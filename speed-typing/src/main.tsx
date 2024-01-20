import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import ErrorPage from "./screen/ErrorPage.tsx";
import Home from "./screen/Home.tsx";
import BaseScreen from "./BaseScreen.tsx";

const router = createBrowserRouter([
	{
		path: "/",
		element: <BaseScreen />,
		errorElement: <ErrorPage />,
		children: [
			{
				index: true,
				element: <Home />,
			},
		],
	},
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
		<ToastContainer />
	</React.StrictMode>
);
