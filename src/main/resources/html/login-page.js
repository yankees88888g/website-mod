const loginButton = document.getElementById("submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    
    const username = document.getElementById("username-field").value;
    const password = document.getElementById("password-field").value;
    const x = username + "Sy0hckO3EJzqTdDZUMO5AIVtTGtuYvdrAJyiYmXXEwD9O6LY8Gr4g451IAVDQrmt⣆⭐⦩≽" + password;    

    window.location.replace('/Login?name=' + x)
    //window.location.replace('/panel')
    /*fetch('/login', {
  method: 'POST',
  headers: {
  },
  body: JSON.stringify({
    x
  })
}).then(userpass => userpass.json()
).then(data => {
    console.log(data);
  });
  if (fetch == true) {
    
      alert("loged in")
  }else{
    alert("nope")
  }*/
}) 