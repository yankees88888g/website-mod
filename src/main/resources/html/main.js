const about = document.getElementById("about");
const map = document.getElementById("map");


about.addEventListener("click"), (e) => {
    e.preventDefault();
    document.location.href = '/about_us.html'
    href = 'about_us.html';
}
map.addEventListener("click"), (e) => {
    e.preventDefault();
    document.location.href = '/map.html'
    href = 'map.html';
}