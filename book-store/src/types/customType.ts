type Review = {
	reviewer: string;
	rating: number;
	comment: string;
};

type Book = {
	title: string;
	author: string;
	genre: string;
	publishedYear: number;
	isbn: string;
	publisher: string;
	pages: number;
	image: string;
	description: string;
	reviews: Review[];
	averageRating: number;
};

type Status = "normal" | "loading" | "error";

interface BookFilter {
	genres: string[];
	author: string;
	publisher: string;
}

export type { Book, Review, Status, BookFilter };
