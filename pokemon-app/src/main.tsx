import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import Home from "./screen/Home.tsx";
import Pokemon from "./screen/Pokemon.tsx";

const router = createBrowserRouter([
	{
		path: "/",
		element: <Home />,
	},
	{
		path: "/:name",
		element: <Pokemon />,
	},
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);
