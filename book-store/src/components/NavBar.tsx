interface MyComponentProps {
	totalBook: number;
	children: React.ReactNode;
}

const NavBar: React.FC<MyComponentProps> = ({ totalBook, children }) => {
	return (
		<ul
			id='nav-bar'
			className='flex justify-between p-3 bg-violet-500 text-white h-15'
		>
			{children}
			<li className='my-auto text-2xl'>
				Total book
				<span className=' ml-2 font-serif italic text-2xl align-super'>
					{totalBook}
				</span>
			</li>
		</ul>
	);
};

export default NavBar;
