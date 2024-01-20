const left = document.querySelector(".side-left");

const handleMove = (event) => {
	left.style.width = `${(event.clientX / window.innerWidth) * 100}%`;
};

document.onmousemove = (event) => handleMove(event);