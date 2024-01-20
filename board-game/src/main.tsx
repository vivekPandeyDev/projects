import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Home, { loader as contactLoader } from "./screen/Home.tsx";
import SingleContact, { deleteAction } from "./components/SingleContact.tsx";
import UpdateContact, {
	action as addAction,
	patchAction,
	loader as updateLoader,
} from "./components/UpdateContact.tsx";
import DiceHomeScreeen from "./screen/DiceHomeScreeen.tsx";
import GameScreen from "./screen/GameScreen.tsx";
import "react-toastify/dist/ReactToastify.css";

const router = createBrowserRouter([
	{
		path: "/",
		element: <Home />,
		loader: contactLoader,
		children: [
			{
				action: addAction,
				path: "contact/add",
				element: <UpdateContact />,
			},
			{
				path: "contact/edit/:id",
				loader: updateLoader,
				action: patchAction,
				element: <UpdateContact type='edit' />,
			},
			{
				path: "contact/:id",
				action: deleteAction,
				element: <SingleContact />,
			},
			{ index: true, element: <div>Home page</div> },
		],
	},
	{
		path: "/game/start",
		element: <DiceHomeScreeen />,
	},
	{
		path: "play",
		element: <GameScreen />,
	},
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);
