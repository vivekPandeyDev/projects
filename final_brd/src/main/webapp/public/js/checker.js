const left = document.querySelector(".side-left");

const handleMove = (event) => {
	left.style.width = `${(event.clientX / window.innerWidth) * 100}%`;
	console.log("width: " + typeof left.style.width)
	let width = parseFloat(left.style.width.substring(0, 5))
	if (width >= 95 || width <= 5) {
		window.location.href = "http://localhost:8080/final_brd/checker/show/table";
	}
};

document.onmousemove = (event) => handleMove(event);