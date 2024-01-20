import { useState } from "react";
import DiceHeader from "../components/DiceHeader";
import DiceBody from "../components/DiceBody";
import { Id, ToastContainer, toast } from "react-toastify";
import { useLocation } from "react-router-dom";
import { GameLevel } from "../types/type";

const diceNumbers = [1, 2, 3, 4, 5, 6];

const GameScreen = () => {
	let toastId: Id;
	const [score, setScore] = useState(0);
	const [userDice, setUserDice] = useState<number>(0);
	const location = useLocation();
	const level: GameLevel = location.state?.level;

	function handleDiceClicked(dice: number) {
		setUserDice(dice);
	}

	function calculateResult(currentDice: number, targetDice: number) {
		console.log(
			`calcuate result currentDice: ${currentDice}, targetDice : ${targetDice}`
		);
		toast.dismiss(toastId);
		if (currentDice == 0) {
			toastId = toast.info("Select a dice number");
			return;
		}

		if (currentDice == targetDice) {
			toastId = toast.success(`You won, point added ${userDice}`, {
				position: "bottom-right",
			});
			setScore((prev) => prev + currentDice);
		} else {
			toastId = toast.error(`You loose,Correct Dice: ${targetDice}`);
			setScore((prev) => prev - 2);
		}
	}

	function resetScore() {
		setScore(0);
		setUserDice(0);
	}
	return (
		<div>
			<h1 className='absolute p-5 font-semibold text-lg'>
				Level :{" "}
				<span className='font-bold text-red-500 uppercase'>
					{level || "default"}
				</span>
			</h1>
			<DiceHeader
				score={score}
				dices={diceNumbers}
				handleDiceSelection={handleDiceClicked}
				currentSelectedDice={userDice}
			/>
			<DiceBody
				handleReset={resetScore}
				userDice={userDice}
				calculateResult={calculateResult}
				level={level}
			/>
			<ToastContainer />
		</div>
	);
};

export default GameScreen;
