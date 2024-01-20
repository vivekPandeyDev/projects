import { useState } from "react";
import { Book } from "../types/customType";

interface MyComponentProps {
	search: string;
	changeSearch: (text: string) => void;
	books: Book[];
	changecurrentBookId: React.Dispatch<React.SetStateAction<string>>;
	currentBookId: string;
}
const Search: React.FC<MyComponentProps> = ({
	search,
	changeSearch,
	books,
	changecurrentBookId,
}) => {
	const filterBooks = books.filter((book) => {
		if (search.length < 2) return true;
		return book.title.toLowerCase().includes(search.toLowerCase());
	});

	const [visible, setVisible] = useState<"hidden" | "visible">("hidden");
	return (
		<div className='relative'>
			<li className='my-auto'>
				<input
					type='text'
					onChange={(e) => {
						if (e.target.value.length < 2) {
							setVisible("hidden");
						} else {
							setVisible("visible");
						}
						changeSearch(e.target.value);
					}}
					value={search}
					className='text-gray-500 focus:font-bold p-2 focus:outline-green-300 rounded-sm w-[600px]'
					placeholder='Search for you favourite book'
				/>
			</li>
			<ul
				className='absolute z-10 w-full h-80 overflow-auto'
				style={{
					visibility: visible,
				}}
			>
				{filterBooks.map((book) => (
					<li
						key={book.isbn}
						className='bg-violet-400 hover:border hover:bg-violet-500 rounded-sm border-black w-full flex py-1 px-2'
						onClick={() => {
							changecurrentBookId(book.isbn);
							setVisible("hidden");
						}}
					>
						<img src={book.image} width={75} />
						<div className='text-gray-800 p-2'>
							<p className='my-auto w-full text-[18px] text-white italic'>
								{book.title}
							</p>
							<p>{book.description}</p>
						</div>
					</li>
				))}
			</ul>
		</div>
	);
};

export default Search;
