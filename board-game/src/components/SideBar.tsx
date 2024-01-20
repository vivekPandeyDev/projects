import { ReactElement } from "react";

type Props = {
	className: string;
	children: ReactElement[] | ReactElement;
};

export default function SideBar({ className, children }: Readonly<Props>) {
	return (
		<div id='sidebar' className={className}>
			{children}
		</div>
	);
}
