import { Outlet } from "react-router-dom";
import Header from "./components/Header";

const BaseScreen = () => {
	return (
		<>
			<Header />
			<Outlet />
		</>
	);
};

export default BaseScreen;
