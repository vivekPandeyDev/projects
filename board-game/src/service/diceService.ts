import dice1 from "../assets/images/dice/dice_1.png";
import dice2 from "../assets/images/dice/dice_2.png";
import dice3 from "../assets/images/dice/dice_3.png";
import dice4 from "../assets/images/dice/dice_4.png";
import dice5 from "../assets/images/dice/dice_5.png";
import dice6 from "../assets/images/dice/dice_6.png";
import { GameLevel } from "../types/type";

export function getDiceImage(dice: number) {
	switch (dice) {
		case 1:
			return dice1;
		case 2:
			return dice2;
		case 3:
			return dice3;
		case 4:
			return dice4;
		case 5:
			return dice5;
		case 6:
			return dice6;
		default:
			return dice1;
	}
}

export function generateRandomNumberWithProbability(
	targetNumber: number,
	level: GameLevel,
	minRange = 1,
	maxRange = 6
) {
	let probability: number;
	switch (level) {
		case "easy":
			probability = 0.8;
			break;
		case "medium":
			probability = 0.6;
			break;
		case "hard":
			probability = 0.3;
			break;
		default:
			probability = 0.5;
	}

	console.log("probability", probability);
	// Adjust the probability to be between 0 and 1
	probability = Math.min(1, Math.max(0, probability));

	// Generate a random number between 0 and 1
	const randomValue = Math.random();

	// Check if the random value is less than the probability
	if (randomValue < probability) {
		// If true, return the target number
		return targetNumber;
	} else {
		// If false, return a random number between 1 and 100 (or any desired range)
		return Math.floor(Math.random() * (maxRange - minRange + 1)) + minRange;
	}
}

export function getRandomIntRange(num: number) {
	return (Math.floor(Math.random() * 10) % num) + 1;
}
