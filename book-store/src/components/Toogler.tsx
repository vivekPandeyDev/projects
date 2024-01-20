interface Props {
	toogle: boolean;
	toogleBack: () => void;
}

const Toogler: React.FC<Props> = ({ toogle, toogleBack }) => {
	return (
		<div className='relative'>
			<span
				className='text-3xl absolute end-0 -top-4'
				onClick={toogleBack}
			>
				{toogle ? <span>😞 </span> : <span>😄 </span>}
			</span>
		</div>
	);
};

export default Toogler;
