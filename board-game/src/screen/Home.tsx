import { Outlet, useLoaderData, useNavigation } from "react-router-dom";
import Main from "../components/Main";
import SideBar from "../components/SideBar";
import SideBarContact from "../components/SideBarContact";
import SideBarTop from "../components/SideBarTop";
import { getAllContact } from "../service/contactService";
import { LoaderData } from "../types/type";

export async function loader() {
	console.log("home component loader runs..");
	return getAllContact();
}

export default function Home() {
	const { data, error } = useLoaderData() as LoaderData<typeof loader>;
	const { state } = useNavigation();

	if (state === "loading") {
		return <div>Loading ...</div>;
	}
	if (error) {
		return <div>Something went wrong</div>;
	}

	return (
		<div className='flex h-screen'>
			<SideBar className='bg-slate-300  p-2'>
				<SideBarTop />
				<SideBarContact contacts={data} />
			</SideBar>

			<Main className='p-2 flex-grow ml-2'>
				<Outlet context={data} />
			</Main>
		</div>
	);
}
