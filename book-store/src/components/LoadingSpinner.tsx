interface Props {
	size?: "sm" | "md" | "lg";
}

const LoadingSpinner: React.FC<Props> = ({ size = "sm" }) => {
	let newStyle = "";
	switch (size) {
		case "sm":
			newStyle = "w-12 h-12";
			break;
		case "md":
			newStyle = "w-20 h-20";
			break;
		case "lg":
			newStyle = "w-32 h-32";
			break;
		default:
			console.log("not a a valid size for loading spinner");
			break;
	}
	return (
		<div className='flex items-center justify-center h-screen'>
			<div
				className={
					"border-t-4 border-blue-500 border-solid rounded-full animate-spin " +
					newStyle
				}
			></div>
		</div>
	);
};

export default LoadingSpinner;
