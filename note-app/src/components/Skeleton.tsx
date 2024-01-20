interface Props {
	size?: number;
}

const Skeleton = ({ size = 1 }: Props) => {
	return (
		<>
			{[...Array(size).keys()].map((i) => (
				<div
					className='skeleton max-w-2xl w-[45rem] h-48'
					key={i}
				></div>
			))}
		</>
	);
};

export default Skeleton;
