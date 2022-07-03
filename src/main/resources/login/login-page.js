const loginButton = document.getElementById("submit");
const loginErrorMsg = document.getElementById("login-error-msg");

// When the login button is clicked, the following code is executed
loginButton.addEventListener("click", (e) => {
    // Prevent the default submission of the form
    e.preventDefault();
    // Get the values input by the user in the form fields
    
    const username = document.getElementById("username-field").value;
    const password = document.getElementById("password-field").value;
    const x = username + "⣆⭐⦩≽" + password;    

    fetch('/login', {
  method: 'POST',
  headers: {
    'Authorization': 'MySuperCoolKey123',
    'content-type': 'application/json'
  },
  body: JSON.stringify({
    x
  })
}).then(data => {
    console.log(data);
  });
  if (fetch == true) {
    
      alert("loged in")
  }else{
    alert("nope")
  }
})