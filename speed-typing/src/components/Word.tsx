import { memo, useRef } from "react";

interface Props {
	text: string;
	correct?: boolean;
	active: boolean;
}

const Word = ({ text, correct, active }: Props) => {
	let currentStyle = "";
	if (active) {
		currentStyle += "text-accent  ";
	} else if (correct === true) {
		currentStyle += "text-success ";
	} else if (correct === false) {
		currentStyle += "text-error ";
	}

	return <span className={currentStyle}>{text} </span>;
};

export default Word;
export const MemoWord = memo(Word);
