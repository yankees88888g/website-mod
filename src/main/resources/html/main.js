const about = document.getElementById("about");
const map = document.getElementById("map");


about.addEventListener("click"), (e) => {
    e.preventDefault();
    
    window.location.replace('/about_us')
}
map.addEventListener("click"), (e) => {
    e.preventDefault();
    window.location.replace('/map')
}