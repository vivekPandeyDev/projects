import { Contact } from "../types/type";
import { fetchData } from "./apiService";

async function getAllContact() {
	return await fetchData<Contact>("http://localhost:3000/contacts");
}

async function deleteContact(url: string) {
	const data = await fetch(url, {
		method: "DELETE",
	});
	return data;
}

async function changeContact(
	url: string,
	contact: Contact,
	method: "POST" | "PATCH" = "POST"
) {
	let data: Response;
	console.log("method", method);
	if (method !== "POST") {
		data = await fetch(url, {
			method: "PATCH",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(contact),
		});
		return data;
	}

	data = await fetch(url, {
		method: method,
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(contact),
	});
	console.log("contact service", data);
	return data;
}

export { getAllContact, changeContact, deleteContact };
