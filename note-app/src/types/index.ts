type NoteType = {
	id: number;
	title: string;
	content: string;
	category: number;
	created_at: string;
	updated_at?: string;
};

type CategoryType = {
	id: number;
	category_name: string;
	priority: string;
};

type AuthError = {
	email?: string;
	password?: string;
	message?: string;
};

export type { NoteType, CategoryType, AuthError };
