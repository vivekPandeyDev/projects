import { useEffect, useState } from "react";
import { Book } from "../types/customType";

const useBookStorage = (key: string) => {
	const [readbooks, setReadBooks] = useState<Book[]>([]);
	useEffect(() => {
		const data = localStorage.getItem(key);
		if (!data) {
			localStorage.setItem(key, JSON.stringify([]));
			return;
		}
		setReadBooks(JSON.parse(data));
	}, [key]);

	const addToReadBooks = (book: Book) => {
		if (readbooks.some((readBook) => readBook.isbn === book.isbn)) {
			alert("already marked favourite");
		} else {
			// alert("added to favourite");
			setReadBooks([...readbooks, book]);
			localStorage.setItem(key, JSON.stringify([...readbooks, book]));
		}
	};

	const removeFromReadBooks = (book: Book) => {
		const updatedBooks = readbooks.filter(
			(readbook) => readbook.isbn !== book.isbn
		);
		setReadBooks(updatedBooks);
		localStorage.setItem(key, JSON.stringify(updatedBooks));
		// alert("removed from favourite");
	};
	return { readbooks, addToReadBooks, removeFromReadBooks  };
};

export default useBookStorage;
