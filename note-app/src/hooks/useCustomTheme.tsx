import { useEffect, useState } from "react";

const useCustomTheme = (themesList: string[]) => {
	const [theme, setTheme] = useState("");
	useEffect(() => {
		const newTheme = themesList.find((item) => theme === item);
		document
			.querySelector("html")
			?.setAttribute("data-theme", newTheme ?? "light");
	}, [theme, themesList]);
	return setTheme;
};

export default useCustomTheme;
