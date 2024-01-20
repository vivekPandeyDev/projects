import useToogle from "../hooks/useToogle";
import { Book } from "../types/customType";

interface Props {
	book: Book;
	changeBook: React.Dispatch<React.SetStateAction<string>>;
	currentBookId: string;
}

const RenderItem: React.FC<Props> = ({ book, changeBook, currentBookId }) => {
	useToogle();

	return (
		<li
			className='flex gap-3 py-2'
			onClick={() => {
				if (currentBookId === book.isbn) changeBook("");
				else changeBook(book.isbn);
			}}
		>
			<div>
				<img className='w-32 h-32' src={book.image}></img>
			</div>
			<div className='my-auto p-3'>
				<h2 className='text-2xl'>
					{book.title} <span className='italic'>({book.genre})</span>
				</h2>

				<p style={{ fontSize: "14px" }}>{book.description}</p>
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
				</div>
			</div>
		</li>
	);
};

export default RenderItem;
