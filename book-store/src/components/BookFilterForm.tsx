import { useState } from "react";
import { Book, BookFilter } from "../types/customType";
import { applyBookFilter } from "../service/bookfilter";

interface Props {
	books: Book[];
	setBooks: React.Dispatch<React.SetStateAction<Book[]>>;
}

const BookFilterForm: React.FC<Props> = ({ books, setBooks }) => {
	const [formData, setFormData] = useState({
		genres: [] as string[],
		author: "",
		publisher: "",
	});

	const [hideGenre, setHideGenre] = useState(true);

	const bookGenresList = [...new Set(books.map((book) => book.genre))];
	const bookAuthorList = [...new Set(books.map((book) => book.author))];
	const bookPublisherList = [...new Set(books.map((book) => book.publisher))];

	const handleFilterSubmit = (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();

		const newBook = applyBookFilter(books, formData as BookFilter);

		setBooks(newBook);
	};

	const handleChange = (
		e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
	) => {
		const { name, value } = e.target;
		setFormData((prevValues) => ({
			...prevValues,
			[name]: value,
		}));
	};

	const handlegenresChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
		const selectedgenres = Array.from(e.target.options)
			.filter((option) => option.selected && option.value.length > 0)
			.map((option) => option.value);

		setFormData((prevValues) => ({
			...prevValues,
			genres: selectedgenres,
		}));
	};

	return (
		<form
			onSubmit={handleFilterSubmit}
			className='p-6 transition-all duration-500 ease-in-out'
		>
			<div className='grid grid-cols-2 gap-3'>
				<div id='genre' className='relative col-span-2'>
					<button
						type='button'
						className='w-full text-sm border bg-white text-gray-500 mb-4 h-10 rounded-md'
						onClick={() => setHideGenre(false)}
					>
						{formData.genres.length === 0 && (
							<span className='text-lg'>Select Genre </span>
						)}
						{formData.genres.map((genre) => `👉${genre}`)}
					</button>
					<select
						name='genres'
						className='w-full border rounded-sm focus:outline-none focus:ring focus:border-green-200  absolute left-0 top-7'
						onChange={handlegenresChange}
						value={formData.genres}
						multiple
						hidden={hideGenre}
						onBlur={() => setHideGenre(true)}
					>
						<option value=''>Select genres</option>
						{bookGenresList.map((bookgenres) => (
							<option
								key={bookgenres}
								value={bookgenres}
								style={{
									backgroundColor: formData.genres.includes(
										bookgenres
									)
										? "#a5d8f3"
										: "transparent",
								}}
							>
								{bookgenres}
							</option>
						))}
					</select>
				</div>
				<div id='author'>
					<select
						name='author'
						value={formData.author}
						onChange={handleChange}
						className='w-full px-4 py-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300'
						placeholder='author'
					>
						<option value=''>Select Author</option>
						{bookAuthorList.map((str) => (
							<option key={str} value={str}>
								{str}
							</option>
						))}
					</select>
				</div>
				<div id='publisher' className=''>
					<select
						name='publisher'
						value={formData.publisher}
						onChange={handleChange}
						className='w-full px-4 py-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300'
						placeholder='author'
					>
						<option value=''>Select Publisher</option>
						{bookPublisherList.map((str) => (
							<option key={str} value={str}>
								{str}
							</option>
						))}
					</select>
				</div>
			</div>
			<div className='flex gap-2'>
				<button
					type='submit'
					className='bg-green-500 hover:bg-green-700 text-white font-bold py-3 px-6 rounded-md text-center w-full transition duration-300  mt-4'
				>
					Apply Filters
				</button>
				<button
					type='button'
					className='bg-red-500 hover:bg-red-700 text-white font-bold py-3 px-6 rounded-md text-center w-full transition duration-300  mt-4'
					onClick={() => {
						const newBook = applyBookFilter(books, {
							genres: [],
							author: "",
							publisher: "",
						});
						setFormData({
							genres: [],
							author: "",
							publisher: "",
						});
						setBooks(newBook);
					}}
				>
					Clear Filter
				</button>
			</div>
		</form>
	);
};

export default BookFilterForm;
