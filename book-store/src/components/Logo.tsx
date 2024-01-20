import imageUrl from "../assets/picture/book-logo.png";

const Logo = () => {
	return (
		<li>
			<img src={imageUrl} alt='db logo' className='h-16 transparent' />
		</li>
	);
};

export default Logo;
