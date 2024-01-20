import { Book } from "../types/customType";

interface Props {
	book: Book;
	changeBook: React.Dispatch<React.SetStateAction<string>>;
	addToReadList: () => void;
	removeFromReadList: () => void;
	isMarkedFavourite: boolean;
}

const SingleBook: React.FC<Props> = ({
	book,
	changeBook,
	addToReadList,
	removeFromReadList,
	isMarkedFavourite,
}) => {
	if (!book) {
		return null;
	}
	return (
		<div className='w-1/2'>
			<button
				className='bg-gray-500 hover:bg-gray-700 focus:outline-none focus:ring focus:border-blue-300 text-white font-bold py-2 px-3 rounded'
				onClick={() => {
					changeBook("");
				}}
			>
				Back
			</button>
			<div className='flex justify-center'>
				<img width={180} src={book.image} alt={book.title}></img>
			</div>
			<div className='my-auto p-3'>
				<h2 className='text-2xl text-center'>
					{book.title} <span className='italic'>({book.genre})</span>
					{isMarkedFavourite ? (
						<span
							className='text-yellow-500 text-3xl align-super'
							onClick={() => {
								removeFromReadList();
							}}
						>
							★
						</span>
					) : (
						<span
							className='text-gray-500 text-3xl align-super'
							onClick={() => {
								addToReadList();
							}}
						>
							✩
						</span>
					)}
				</h2>

				<p className='text-1xl'>{book.description}</p>

				<div className='flex justify-between '>
					<p>
						Year:
						<span className='text-gray-500 ml-2'>
							{book.publishedYear}
						</span>
					</p>
					<p>
						Publisher:
						<span className='text-gray-500 ml-2'>
							{book.publisher}
						</span>
					</p>
					<p className='italic'>
						Total Pages :{" "}
						<span className='text-gray-500 ml-2'>{book.pages}</span>
					</p>
				</div>
			</div>
		</div>
	);
};

export default SingleBook;
