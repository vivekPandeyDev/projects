import { useTypewriter } from "../hooks/useTypewriter";

interface Props {
	text: string;
	delay?: number;
	uppercase?: boolean;
	italic?: boolean;
}

const Typewriter = ({
	text,
	delay = 50,
	uppercase = false,
	italic = false,
}: Props) => {
	const displayText = useTypewriter(text, delay);
	let style = " text-accent blinking-cursor  ";
	if (uppercase) {
		style += "uppercase ";
	}
	if (italic) {
		style += "italic ";
	}
	return <p className={style}>{displayText}</p>;
};

export default Typewriter;
