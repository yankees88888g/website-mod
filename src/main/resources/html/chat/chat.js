const form = document.getElementById('form');

form.addEventListener('submit', function(e) {
    e.preventDefault()
    const payload = new FormData(form);
fetch('/https://httpbin.org/post', {
  method: 'POST',
  headers: {
    'Authorization': 'MySuperCoolKey123',
    'content-type': 'application/json'
  },
  body: JSON.stringify({
    payload
  })
}).then(data => {
    console.log(data);
  });
})