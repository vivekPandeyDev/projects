import { CategoryType, NoteType } from "../types";

export async function getAllNote(
	page = 1,
	limit = 5,
	searchText = ""
): Promise<NoteType[]> {
	const data = await fetch(
		`http://localhost:3000/notes?_page=${page}&_limit=${limit}&title_like=${searchText}`
	);
	if (!data.ok) {
		throw new Error("cannot fetch notes");
	}
	return await data.json();
}

export async function deleteNote(id: number) {
	const resp = await fetch(`http://localhost:3000/notes/${id}`, {
		method: "delete",
	});
	if (!resp.ok) {
		throw new Error("error while deleting notes");
	}
	return await resp.json();
}

export async function updateNote(
	id: number,
	data: NoteType
): Promise<NoteType> {
	const resp = await fetch(`http://localhost:3000/notes/${id}`, {
		method: "put",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	});
	if (!resp.ok) {
		throw new Error("error while deleting notes");
	}
	return await resp.json();
}

export async function createNote(data: NoteType) {
	const res = await fetch("http://localhost:3000/notes", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	});

	if (!res.ok) {
		throw new Error("cannot add new note");
	}

	return await res.json();
}

export async function getCategoryById(id: number): Promise<CategoryType> {
	const data = await fetch(`http://localhost:3000/categories/${id}`);
	if (!data.ok) {
		throw new Error("cannot fetch category");
	}
	return await data.json();
}

export async function getAllCategory(): Promise<CategoryType[]> {
	const data = await fetch("http://localhost:3000/categories");
	if (!data.ok) {
		throw new Error("cannot fetch category");
	}
	return await data.json();
}
