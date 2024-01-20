import { ReactElement } from "react";
import { NavLink } from "react-router-dom";

type Props = {
	className: string;
	children: ReactElement[] | ReactElement;
};

export default function Main({ className, children }: Readonly<Props>) {
	return (
		<div className={className}>
			<NavLink to='..' relative={"path"} className='btn btn-link'>
				Back
			</NavLink>
			{children}
		</div>
	);
}
