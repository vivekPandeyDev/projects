import { Book, BookFilter } from "../types/customType";

export function applyBookFilter(books: Book[], filter: BookFilter): Book[] {
	return books.filter((book) => {
		//default
		if (
			filter.genres.length === 0 &&
			filter.author === "" &&
			filter.publisher === ""
		) {
			return true;
		}
		// other
		let isPassed = true;
		if (filter.genres.length === 0) {
			if (filter.author !== "" && filter.author != book.author) {
				isPassed = false;
			}
			if (
				filter.publisher !== "" &&
				filter.publisher !== book.publisher
			) {
				isPassed = false;
			}
		} else {
			if (!filter.genres.some((genre) => genre === book.genre)) {
				isPassed = false;
			}
		}

		return isPassed;
	});
}
