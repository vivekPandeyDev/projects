import { KeyboardEvent, useEffect, useRef, useState } from "react";
import HeroText from "./HeroText";
import { content, timeLimit } from "../utils/randomUtil";
let keyStrokes = 0;
let intervalId = 0;

const Hero = () => {
	const [activeWordIndex, setActiveWordIndex] = useState(0);
	const [correctIndexList, setCorrectIndexList] = useState<number[]>([]);
	const [isTestStarted, setIsTestStarted] = useState(false);
	const [countDown, setCountDown] = useState(timeLimit[0]);
	const focusInput = useRef<HTMLInputElement>(null);
	const accuracyPercentage = correctIndexList.length / activeWordIndex;
	const wpm = (keyStrokes / countDown) * 60 * accuracyPercentage;

	useEffect(() => {
		focusInput.current && focusInput.current.focus();
	}, []);

	useEffect(() => {
		if (countDown <= 0) {
			clearInterval(intervalId);
		}
	}, [countDown]);

	function handlePress(e: KeyboardEvent<HTMLInputElement>) {
		console.log("key stroke", keyStrokes);

		const userInput = (e.target as HTMLInputElement).value;

		if (e.code === "Backspace" && keyStrokes > 0) {
			keyStrokes -= 1;
		} else {
			keyStrokes += 1;
		}
		// if user pressed space
		if (e.code === "Space") {
			// if we can increase current index count
			if (activeWordIndex < content.length) {
				setActiveWordIndex((prev) => prev + 1);
				// increasing correctIndexList when use enter correct answer
				if (content[activeWordIndex] === userInput.trim()) {
					setCorrectIndexList([...correctIndexList, activeWordIndex]);
				}
			}
			(e.target as HTMLInputElement).value = "";
			focusInput.current?.classList.add("animate-shake");
		} else {
			focusInput.current?.classList.remove("animate-shake");
		}
	}

	function handleTimeOut(e: unknown) {
		if (e.target !== e.currentTarget && e.target.id !== "start-timer") {
			if (!isTestStarted) setCountDown(+e.target.textContent);
		}
	}

	return (
		<div className='hero min-h-screen'>
			<div className='hero-content'>
				<div className='max-w-screen-lg flex flex-col gap-10'>
					<input
						type='text'
						className='border border-primary rounded-md p-2 text-primary text-center text-xl '
						onKeyUp={handlePress}
						ref={focusInput}
					/>
					<HeroText
						activeWordIndex={activeWordIndex}
						correctIndexList={correctIndexList}
						content={content}
					/>
					<h3>{countDown} s</h3>
					<p className='flex gap-2'>
						<span>WPM: {wpm ? wpm.toFixed(2) : 0}</span>
						<span>
							Accuracy:{" "}
							{accuracyPercentage
								? (accuracyPercentage * 100).toFixed(2)
								: 0}
							%
						</span>
					</p>
					<p
						className='flex justify-center gap-3'
						onClick={handleTimeOut}
					>
						<span
							onClick={() => {
								if (!isTestStarted) {
									console.log("start start span");
									setIsTestStarted(true);
									intervalId = setInterval(() => {
										setCountDown((prev) => prev - 11);
									}, 1000);
								}
							}}
							id='start-timer'
							className='cursor-pointer'
						>
							🔄
						</span>
						{timeLimit.map((time) => (
							<span key={time} className='cursor-pointer'>
								{time}
							</span>
						))}
					</p>
					<p className='text-center'> normal hard | eng hi</p>
				</div>
			</div>
		</div>
	);
};

export default Hero;
