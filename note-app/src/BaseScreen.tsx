import { LoaderFunctionArgs, Outlet, redirect } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { ToastContainer, toast } from "react-toastify";
import { supabase } from "./service/client";
import { WHITELIST_URL } from "./utils/Constants";
import { isLoggedIn } from "./utils";

// eslint-disable-next-line react-refresh/only-export-components
export async function loader({ request }: LoaderFunctionArgs) {
	const url = new URL(request.url);
	if (WHITELIST_URL.find((item) => item === url.pathname)) {
		return null;
	}

	if (!isLoggedIn()) {
		toast.info("you are not logged in redirecting");
		// check for authentication user
		const {
			data: { user },
		} = await supabase.auth.getUser();
		if (!user) {
			return redirect("/login");
		}
	}

	return null;
}

const BaseScreen = () => {
	return (
		<>
			<Header loggedIn={isLoggedIn()} />
			<Outlet />
			<Footer />
			<ToastContainer />
		</>
	);
};

export default BaseScreen;
