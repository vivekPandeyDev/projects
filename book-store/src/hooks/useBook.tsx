import { useEffect, useState } from "react";
import { Book, Status } from "../types/customType";
import { generateFakeData } from "../assets/book";

function shuffleArray<T>(array: T[]) {
	for (let i = array.length - 1; i > 0; i--) {
		const j = Math.floor(Math.random() * (i + 1));
		[array[i], array[j]] = [array[j], array[i]];
	}
}

const useBook = () => {
	const [books, setBooks] = useState<Book[]>([]);
	const [status, setStatus] = useState<Status>("loading");
	const [initialLoading, setInitaialLoading] = useState(true);
	useEffect(() => {
		setStatus("loading");
		generateFakeData()
			.then((data) => {
				shuffleArray(data.books);
				setBooks(data.books);
				setStatus("normal");
				setInitaialLoading(false);
			})
			.catch(() => {
				setStatus("error");
				setInitaialLoading(false);
			});
	}, []);

	return { books, status, initialLoading, setBooks };
};

export default useBook;
