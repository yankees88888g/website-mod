package net.web.fabric.http.website.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class HandlerWithServletSupport implements HttpHandler {

    private HttpServlet servlet;

    private final class RequestWrapper extends HttpServletRequestWrapper {
        private final HttpExchange ex;
        private final Map<String, String[]> postData;
        private final ServletInputStream is;
        private final Map<String, Object> attributes = new HashMap<>();

        private RequestWrapper(HttpServletRequest request, HttpExchange ex, Map<String, String[]> postData, ServletInputStream is) {
            super(request);
            this.ex = ex;
            this.postData = postData;
            this.is = is;
        }

        @Override
        public String getHeader(String name) {
            return ex.getRequestHeaders().getFirst(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            return new Vector<String>(ex.getRequestHeaders().get(name))
                    .elements();
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return new Vector<String>(ex.getRequestHeaders().keySet())
                    .elements();
        }

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public void setAttribute(String name, Object o) {
            this.attributes.put(name, o);
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return new Vector<String>(attributes.keySet()).elements();
        }

        @Override
        public String getMethod() {
            return ex.getRequestMethod();
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return is;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(
                    getInputStream()));
        }

        @Override
        public String getPathInfo() {
            return ex.getRequestURI().getPath();
        }

        @Override
        public String getParameter(String name) {
            String[] arr = postData.get(name);
            return arr != null ? (arr.length > 1 ? Arrays.toString(arr)
                    : arr[0]) : null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return postData;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Vector<String>(postData.keySet()).elements();
        }
    }

    private final class ResponseWrapper extends HttpServletResponseWrapper {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final ServletOutputStream servletOutputStream = new ServletOutputStream() {

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }
        };

        private final HttpExchange ex;
        private final PrintWriter printWriter;
        private int status = HttpServletResponse.SC_OK;

        private ResponseWrapper(HttpServletResponse response,
                                HttpExchange ex) {
            super(response);
            this.ex = ex;
            printWriter = new PrintWriter(servletOutputStream);
        }

        @Override
        public void setContentType(String type) {
            ex.getResponseHeaders().add("Content-Type", type);
        }

        @Override
        public void setHeader(String name, String value) {
            ex.getResponseHeaders().add(name, value);
        }

        @Override
        public javax.servlet.ServletOutputStream getOutputStream()
                throws IOException {
            return servletOutputStream;
        }

        @Override
        public void setContentLength(int len) {
            ex.getResponseHeaders().add("Content-Length", len + "");
        }

        @Override
        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {
            this.status = sc;
            if (msg != null) {
                printWriter.write(msg);
            }
        }

        @Override
        public void sendError(int sc) throws IOException {
            sendError(sc, null);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return printWriter;
        }

        public void complete() throws IOException {
            try {
                printWriter.flush();
                ex.sendResponseHeaders(status, outputStream.size());
                if (outputStream.size() > 0) {
                    ex.getResponseBody().write(outputStream.toByteArray());
                }
                ex.getResponseBody().flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ex.close();
            }
        }
    }

    public HandlerWithServletSupport(HttpServlet servlet) {
        this.servlet = servlet;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void handle(final HttpExchange ex) throws IOException {
        byte[] inBytes = getBytes(ex.getRequestBody());

        final ByteArrayInputStream newInput = new ByteArrayInputStream(inBytes);
        final ServletInputStream is = new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return newInput.read();
            }
        };

        Map<String, String[]> parsePostData = new HashMap<>();

        try {
            parsePostData.putAll(HttpUtils.parseQueryString(ex
                    .getRequestURI().getQuery()));


            parsePostData.putAll(HttpUtils.parsePostData(inBytes.length, is));
        } catch (IllegalArgumentException e) {
            newInput.reset();
        }
        final Map<String, String[]> postData = parsePostData;

        RequestWrapper req = new RequestWrapper(
                createUnimplementAdapter(HttpServletRequest.class), ex, postData, is);

        ResponseWrapper resp = new ResponseWrapper(
                createUnimplementAdapter(HttpServletResponse.class), ex);

        try {
            servlet.service(req, resp);
            resp.complete();
        } catch (ServletException e) {
            throw new IOException(e);
        }
        try {
            doGet(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        OutputStream os = ex.getResponseBody();
        String response = "test";
        ex.getRequestBody().close();
        ex.sendResponseHeaders(200, response.length());
        os.write(response.getBytes());
        os.close();
    }
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "Login successful" ;// a session exists
        } else {
            return "Login failed try again or reset it";// no session
        }
    }
    private static byte[] getBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int r = in.read(buffer);
            if (r == -1)
                break;
            out.write(buffer, 0, r);
        }
        return out.toByteArray();
    }

    @SuppressWarnings("unchecked")
    private static <T> T createUnimplementAdapter(Class<T> httpServletApi) {
        class UnimplementedHandler implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                throw new UnsupportedOperationException("Not implemented: "
                        + method + ", args=" + Arrays.toString(args));
            }
        }

        return (T) Proxy.newProxyInstance(
                UnimplementedHandler.class.getClassLoader(),
                new Class<?>[] { httpServletApi },
                new UnimplementedHandler());
    }

}