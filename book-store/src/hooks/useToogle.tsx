import { useState } from "react";

const useToogle = () => {
	const [toogle, setToogle] = useState(false);
	function toogleBack() {
		setToogle(!toogle);
	}
	return { toogle, toogleBack };
};

export default useToogle;
