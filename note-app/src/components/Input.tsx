import { ChangeEvent, useEffect, useRef } from "react";

interface Props {
	setSearchText: React.Dispatch<React.SetStateAction<string>>;
}

const Input = ({ setSearchText }: Props) => {
	const searchRef = useRef<HTMLInputElement>(null);

	function handleSearchInputChange(e: ChangeEvent<HTMLInputElement>) {
		setSearchText(e.target.value);
	}

	useEffect(() => {
		searchRef.current?.focus();
	}, []);

	return (
		<input
			type='text'
			placeholder='Search Todo title here...'
			className='input input-bordered  w-full focus:border-none font-bold text text-gray-600'
			onChange={handleSearchInputChange}
			ref={searchRef}
		/>
	);
};

export default Input;
