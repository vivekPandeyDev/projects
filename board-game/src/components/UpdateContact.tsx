import {
	Form,
	LoaderFunctionArgs,
	redirect,
	useActionData,
	useLoaderData,
} from "react-router-dom";
import { Contact } from "../types/type";
import { changeContact, getAllContact } from "../service/contactService";

export async function action({ request, params }: LoaderFunctionArgs) {
	const id = params.id;
	if (!id) {
		throw new Error("No id found");
	}
	const form = await request.formData();
	const myData: Contact = {
		firstName: form.get("firstName")?.toString() ?? "",
		lastName: form.get("lastName")?.toString() ?? "",
		avatar: form.get("avatar")?.toString() ?? "",
		notes: form.get("notes")?.toString() ?? "",
		favorite: false,
		twitter: form.get("twitter")?.toString() ?? "",
		id: +id | 1,
	};

	const data = await changeContact("http://localhost:3000/contacts", myData);
	if (!data.ok) {
		return { error: data.statusText };
	} else {
		console.log("redirection ..........");
		throw redirect("/");
	}
}

export async function patchAction({ request, params }: LoaderFunctionArgs) {
	const id = params.id;
	if (!id) {
		throw new Error("No id found");
	}
	const form = await request.formData();

	const myData: Contact = {
		firstName: form.get("firstName")?.toString() ?? "",
		lastName: form.get("lastName")?.toString() ?? "",
		avatar: form.get("avatar")?.toString() ?? "",
		notes: form.get("notes")?.toString() ?? "",
		favorite: false,
		twitter: form.get("twitter")?.toString() ?? "",
		id: +id | 1,
	};
	const data = await changeContact(
		`http://localhost:3000/contacts/${params.id}`,
		myData,
		"PATCH"
	);
	if (!data.ok) {
		return { error: data.statusText };
	} else {
		throw redirect("/");
	}
}

export async function loader({ params }: LoaderFunctionArgs) {
	if (!params.id) {
		throw new Error("no params id found");
	}
	const id = params.id;
	const contacts = await getAllContact();
	return contacts.data.find((contact) => contact.id === +id);
}

type Prop = {
	type?: "add" | "edit";
};

export default function UpdateContact({ type = "add" }: Readonly<Prop>) {
	const data = useActionData() as { error: string };
	const contact = useLoaderData() as Contact;
	return (
		<div className='bg-gray-400 grid place-items-center h-full'>
			<div className='card lg:card-side bg-base-100 shadow-xl'>
				<figure>
					<img
						src='https://daisyui.com/images/stock/photo-1494232410401-ad00d5433cfa.jpg'
						alt='Album'
					/>
				</figure>
				<div className='card-body'>
					<h2 className='card-title m-3'>Add New Contact!</h2>
					<Form method={type === "add" ? "POST" : "PATCH"}>
						{data && (
							<div
								role='alert'
								className='alert alert-error p-2 mb-2'
							>
								<svg
									xmlns='http://www.w3.org/2000/svg'
									className='stroke-current shrink-0 h-6 w-6'
									fill='none'
									viewBox='0 0 24 24'
								>
									<path
										strokeLinecap='round'
										strokeLinejoin='round'
										strokeWidth='2'
										d='M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z'
									/>
								</svg>
								<span>{data?.error}</span>
							</div>
						)}

						<div className='grid grid-cols-1 gap-2'>
							<input name='type' defaultValue={type} hidden />
							<input
								type='text'
								placeholder='Type here firstName'
								className='input w-[400px] input-bordered'
								name='firstName'
								defaultValue={contact?.firstName || ""}
								required
							/>
							<input
								type='text'
								placeholder='Type here lastName'
								className='input w-[400px]  input-bordered'
								name='lastName'
								defaultValue={contact?.lastName || ""}
								required
							/>
							<input
								type='url'
								placeholder='Type here avatar url'
								className='input w-[400px]  input-bordered'
								name='avatar'
								defaultValue={contact?.avatar ?? ""}
								required
							/>
							<input
								type='text'
								placeholder='Type here twitter handler'
								className='input w-[400px]  input-bordered'
								name='twitter'
								defaultValue={contact?.twitter || ""}
								required
							/>
							<textarea
								className='textarea textarea-ghost'
								placeholder='Type here notes'
								name='notes'
								defaultValue={contact?.notes || ""}
								required
							></textarea>
							<button type='submit' className='btn btn-success'>
								Submit
							</button>
						</div>
					</Form>
				</div>
			</div>
		</div>
	);
}
