type Props = {
	score: number;
	dices: number[];
	handleDiceSelection: (dice: number) => void;
	currentSelectedDice: number;
};

export default function DiceHeader({
	score,
	dices,
	handleDiceSelection,
	currentSelectedDice,
}: Readonly<Props>) {
	return (
		<div
			id='game-header'
			className='container mx-auto grid grid-cols-1  lg:grid-cols-[minmax(200px,400px)_1fr] px-4'
		>
			<div id='game-score'>
				<h1 className='text-[9rem] px-2 text-center relative'>
					{score}
					<span className='font-semibold text-lg absolute left-[43%] md:left-[39%] bottom-5'>
						Total Score
					</span>
				</h1>
			</div>
			<div id='dice-select' className='my-14'>
				<div className='flex gap-3'>
					{dices.map((dice) => (
						<button
							key={dice}
							className={`border text-[4rem] flex-grow text-center  ${
								dice === currentSelectedDice && "bg-red-400"
							}`}
							onClick={() => handleDiceSelection(dice)}
						>
							{dice}
						</button>
					))}
				</div>
				<p className='text-end mt-7 mr-2 text-xl font-bold'>
					Select Number
				</p>
			</div>
		</div>
	);
}
