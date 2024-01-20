import {
	Form,
	Link,
	LoaderFunctionArgs,
	redirect,
	useOutletContext,
	useParams,
} from "react-router-dom";
import { Contact } from "../types/type";
import { deleteContact } from "../service/contactService";

// eslint-disable-next-line react-refresh/only-export-components
export async function deleteAction({ params }: LoaderFunctionArgs) {
	if (!params.id) {
		return { error: "no id found" };
	}
	const id = +params.id;
	console.log(id);
	const data = await deleteContact(`http://localhost:3000/contacts/${id}`);
	console.log(data);
	if (!data.ok) {
		return { error: data.statusText };
	} else {
		throw redirect("/");
	}
}

export default function SingleContact() {
	const data = useOutletContext<Contact[]>();
	const { id } = useParams();
	const contact = data.find(
		(single) => single.id == (id as unknown as number)
	);

	if (!contact) {
		return <div>No contact with given id found</div>;
	}

	return (
		<div id={`contact-${id}`} className='text-center h-full'>
			<div
				className={`avatar online mt-3 ${
					contact.avatar ? "" : "placeholder"
				}`}
			>
				{contact.avatar ? (
					<div className='w-80 rounded-full ring ring-primary ring-offset-base-100 ring-offset-2'>
						<img
							src={contact.avatar}
							alt='contact image'
							draggable={false}
						/>
					</div>
				) : (
					<div className='w-80 bg-neutral text-neutral-content rounded-full'>
						<span className='text-9xl'>
							{contact.firstName.charAt(0).toUpperCase()}
							{contact.lastName.charAt(0).toUpperCase()}
						</span>
					</div>
				)}
			</div>
			<p>
				Name : {contact.firstName} {contact.lastName}
			</p>
			<div className='h-full flex justify-center p-2'>
				<Link
					to={`/contact/edit/${contact.id}`}
					className='btn btn-outline'
				>
					Edit
				</Link>
				<Form method='post'>
					<button className='btn btn-error mx-4'>Delete</button>
				</Form>
			</div>
		</div>
	);
}
