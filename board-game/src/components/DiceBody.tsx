import { useState } from "react";
import {
	getDiceImage,
	generateRandomNumberWithProbability,
	getRandomIntRange,
} from "../service/diceService";
import { GameLevel } from "../types/type";

type Props = {
	calculateResult: (currentDice: number, targetDice: number) => void;
	handleReset: () => void;
	userDice: number;
	level: GameLevel;
};

export default function DiceBody({
	calculateResult,
	handleReset,
	userDice,
	level,
}: Readonly<Props>) {
	const [toogleHelp, setToogleHelp] = useState(false);
	const [systemDice, setSystemDice] = useState(0);
	const [rotate, setRotate] = useState(false);
	function handleImageClick() {
		setRotate(!rotate);
		const random = generateRandomNumberWithProbability(
			userDice === 0 ? getRandomIntRange(6) : userDice,
			level
		);
		console.log(`highestProbaility: ${userDice} `);
		setSystemDice(random);
		setToogleHelp(false);
		calculateResult(userDice, random);
	}

	return (
		<>
			<div className='container mx-auto card card-compact max-w-[40rem] bg-base-100 shadow-xl '>
				<figure>
					<img
						src={
							systemDice === 0
								? getDiceImage(getRandomIntRange(6))
								: getDiceImage(systemDice)
						}
						alt='Dices'
						onClick={handleImageClick}
						onKeyDown={console.log}
						className={`p-8 transition-all  duration-700 ${
							rotate && "rotate"
						}`}
					/>
				</figure>
				<div className='card-body'>
					<div className='card-actions items-center flex-col gap-3'>
						<button
							className='btn btn-outline'
							onClick={handleReset}
						>
							Reset Score
						</button>
						<button
							className='btn btn-neutral'
							onClick={() => setToogleHelp(!toogleHelp)}
						>
							Show Rules
						</button>
					</div>
				</div>
			</div>
			{toogleHelp && (
				<div
					role='alert'
					className='mx-auto mt-5  card bg-gray-300 max-w-[40rem]'
				>
					<div className='card-body'>
						<span className='card-title'>
							How to play dice game
						</span>
						<span>Select any number</span>
						<span>Click on Dice Image</span>
						<span>
							After click on dice if selected number is equal to
							dice number you will get point
						</span>
						<span>If wrong guess the 2 point will be deducted</span>
					</div>
				</div>
			)}
		</>
	);
}
