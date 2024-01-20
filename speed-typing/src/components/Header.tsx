import { HEADER_SUB_TITLE, HEADER_TITLE } from "../utils/Constants";
import Typewriter from "./Typewriter";

const Header = () => {
	return (
		<div className='container mx-auto text-center'>
			<h1 className='text-2xl font-bold text-warning p-2'>
				{HEADER_TITLE}
			</h1>
			<Typewriter text={HEADER_SUB_TITLE} delay={100} />
		</div>
	);
};

export default Header;
