import { MemoWord } from "./Word";

interface Props {
	activeWordIndex: number;
	correctIndexList: number[];
	content: string[];
}

const HeroText = ({ activeWordIndex, correctIndexList, content }: Props) => {
	return (
		<p className='text-xl '>
			{content.map((c, contentIndex) => (
				<MemoWord
					key={contentIndex}
					active={contentIndex === activeWordIndex}
					correct={
						activeWordIndex < contentIndex
							? undefined
							: correctIndexList.some(
									(item) => item === contentIndex
									// eslint-disable-next-line no-mixed-spaces-and-tabs
							  )
					}
					text={c}
				/>
			))}
		</p>
	);
};

export default HeroText;
