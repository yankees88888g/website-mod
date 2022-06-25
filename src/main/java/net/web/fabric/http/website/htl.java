package net.web.fabric.http.website;

import net.web.fabric.config.file;

public class htl{
    private static boolean booMap = true;//TODO set to false when other TODO is down
    static String map = "<li><a href=" + file.maplink + ">Map</a></li>";
    public static String htl =
            """
                                        
                    <html>
                    <meta charset="UTF-8" />
                        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <nav>
                                    <ul>
                                      <li><strong>Home</strong></li>
                                      <li><a href="about_us.html">About Us</a></li> 
                                      """ //TODO fix ^
                    + (booMap == true ? map : "") + //TODO hook this with the config file
                    """
                                              <li><a href="clients.html">Our Clients</a></li>
                                            </ul>
                                          </nav>
                            <nav>
                              <ul>
                                <li><a href="#">Navigation</a></li>
                                <li><a href="#">Menu</a></li>
                                <li><a href="#">Links</a></li>
                                <li><a href="map">
                                
                             
                                      </a></li>
                                      </ul>
                                    </nav>
                                        <body>
                                            <p>Hello, world</p>
                                        </body>
                                        <a href="https://github.com/yankees88888g/website-mod">This website was made for minecraft servers and was published the the public domain.</a>
                                    </html>
                                    """;
}
