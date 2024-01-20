import { useState } from "react";
import NavBar from "./components/NavBar";
import Search from "./components/Search";
import Logo from "./components/Logo";
import ListBook from "./components/ListBook";
import LoadingSpinner from "./components/LoadingSpinner";
import SingleBook from "./components/SingleBook";
import useBook from "./hooks/useBook";
import useBookStorage from "./hooks/useBookStorage";
import BookFilterForm from "./components/BookFilterForm";

function App() {
	const { books, status, initialLoading, setBooks } = useBook();
	const constantBook = useBook();
	const { readbooks, addToReadBooks, removeFromReadBooks } =
		useBookStorage("readList");
	const [searchString, setsearchString] = useState("");
	const [selectedBookId, setSelectedBookId] = useState("");

	const currentBook = books.filter((book) => book.isbn === selectedBookId)[0];

	return (
		<>
			<NavBar totalBook={books.length}>
				<Logo />
				<Search
					search={searchString}
					changeSearch={setsearchString}
					currentBookId={selectedBookId}
					changecurrentBookId={setSelectedBookId}
					books={books}
				/>
			</NavBar>

			{status === "error" ? (
				<div
					id='no-content'
					className='flex items-center justify-center min-h-screen bg-gray-100'
				>
					<div className='bg-gray-200 p-8 rounded shadow-md w-1/2'>
						<h1 className='text-4xl font-semibold mb-4'>
							Something went wrong
						</h1>
						<p className='text-red-500 mb-4 text-2xl'>
							Cannot fetch book at the moment.
						</p>
						<p className='text-gray-500'>Please try again later.</p>
					</div>
				</div>
			) : initialLoading ? (
				<LoadingSpinner size='lg' />
			) : (
				<div
					id='main-content'
					className='container mx-auto my-4 bg-gray-100 p-3 flex justify-between gap-2'
				>
					<ListBook
						books={books}
						currentBookId={selectedBookId}
						changeBook={setSelectedBookId}
						filter={
							<BookFilterForm
								books={constantBook.books}
								setBooks={setBooks}
							/>
						}
					>
						<h2 className='text-center text-2xl text-green-600 italic'>
							Current Book Available
						</h2>
					</ListBook>
					{selectedBookId.length > 2 ? (
						<SingleBook
							isMarkedFavourite={readbooks.some(
								(book) => book.isbn === currentBook.isbn
							)}
							book={currentBook}
							changeBook={setSelectedBookId}
							addToReadList={() => addToReadBooks(currentBook)}
							removeFromReadList={() => {
								removeFromReadBooks(currentBook);
							}}
						/>
					) : (
						<ListBook
							books={readbooks}
							currentBookId={selectedBookId}
							changeBook={setSelectedBookId}
						>
							<h2 className='text-center text-2xl text-violet-600 italic'>
								Your Favourite Books
							</h2>
						</ListBook>
					)}
				</div>
			)}
		</>
	);
}

export default App;
