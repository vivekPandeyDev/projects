import useToogle from "../hooks/useToogle";
import { Book } from "../types/customType";
import RenderItem from "./RenderItem";
import Toogler from "./Toogler";

interface Props {
	books: Book[];
	changeBook: React.Dispatch<React.SetStateAction<string>>;
	currentBookId: string;
	children: React.ReactNode;
	filter?: React.ReactElement;
}

const ListBook: React.FC<Props> = ({
	books,
	changeBook,
	currentBookId,
	children,
	filter,
}) => {
	const { toogle, toogleBack } = useToogle();


	return (
		<div className='w-1/2 h-[32rem]  overflow-x-auto scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100'>
			{children}
			<Toogler  toogle={toogle} toogleBack={toogleBack} />
			{toogle && filter}
			<ul
				className={`transition-all duration-500 ease-in-out ${
					!toogle
						? "opacity-100 max-h-full"
						: "opacity-0 max-h-0 overflow-hidden"
				}`}
			>
				{books.map((book) => (
					<RenderItem
						key={book.isbn}
						book={book}
						changeBook={changeBook}
						currentBookId={currentBookId}
					/>
				))}
			</ul>
			{books.length === 0 && (
				<div className='flex items-center justify-center h-screen bg-gray-100'>
					<div className='text-center'>
						<div className='animate-bounce text-5xl text-red-500 mb-4'>
							&#128577;
						</div>
						<h2 className='text-3xl font-semibold mb-4 text-gray-800'>
							No Book to Display
						</h2>
						<p className='text-gray-600 mb-8'>
							Please add books to your library or change filter
						</p>
					</div>
				</div>
			)}
		</div>
	);
};

export default ListBook;
