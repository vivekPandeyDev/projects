import { useState, useEffect } from "react";

export const useTypewriter = (text: string, speed: number) => {
	const [displayText, setDisplayText] = useState("");
	const [index, setIndex] = useState(0);

	useEffect(() => {
		let id: number;
		if (index < text.length) {
			id = setTimeout(() => {
				setDisplayText((prev) => prev + text.charAt(index));
				setIndex((prev) => prev + 1);
			}, speed);
		}
		return () => {
			clearTimeout(id);
		};
	}, [index, speed, text]);

	return displayText;
};
