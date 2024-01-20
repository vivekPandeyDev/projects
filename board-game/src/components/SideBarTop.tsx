import { Link } from "react-router-dom";

const SideBarTop = () => {
	return (
		<div className='flex gap-2'>
			<div>
				<input
					type='text'
					placeholder='Search contact ...'
					className='input input-success input-sm o w-full max-w-xs md'
				/>
			</div>
			<div>
				<Link  to={"contact/add"} className='btn btn-sm text-gray-600'>
					New Contact
				</Link>
			</div>
		</div>
	);
};

export default SideBarTop;
