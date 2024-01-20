import { Link } from "react-router-dom";
import { Contact } from "../types/type";

type Props = {
	contacts: Contact[];
};

export default function SideBarContact({ contacts }: Readonly<Props>) {
	return (
		<ul className='p-2 text-[18px] list-disc list-inside text-gray-800 font-medium'>
			{contacts.map((contact) => (
				<li key={contact.id}>
					<Link to={`contact/${contact.id}`}>
						{contact.firstName} {contact.lastName}
					</Link>
				</li>
			))}
		</ul>
	);
}
