const button = document.getElementById("submit");

button.addEventListener("click", (e) => {
    e.preventDefault();
const player = document.getElementById("player").value;

window.location.replace('/admin/inv?player=' + player)

})
