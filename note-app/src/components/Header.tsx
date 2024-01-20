import { ChangeEvent } from "react";
import { LOGO, THEMES, links } from "../utils/Constants";
import { Form, NavLink } from "react-router-dom";
import useCustomTheme from "../hooks/useCustomTheme";
import { supabase } from "../service/client";
import { toast } from "react-toastify";

// eslint-disable-next-line react-refresh/only-export-components
export async function action() {
	console.log("logout");

	const { error } = await supabase.auth.signOut();
	if (error) {
		toast.error(`Cannot logout because ${error.message}`);
		return false;
	} else {
		toast.success("logout completed");
	}

	return true;
}

const Header = ({ loggedIn }: { loggedIn: boolean }) => {
	const setTheme = useCustomTheme(THEMES);

	function changeTheme(e: ChangeEvent<HTMLSelectElement>) {
		setTheme(e.target.value);
	}

	return (
		<header id='header' className='navbar bg-base-100'>
			<div className='navbar-start'>
				<div className='dropdown'>
					<div tabIndex={0} className='btn btn-ghost btn-circle '>
						<svg
							xmlns='http://www.w3.org/2000/svg'
							className='h-6 w-6'
							fill='none'
							viewBox='0 0 24 24'
							stroke='currentColor'
						>
							<path
								strokeLinecap='round'
								strokeLinejoin='round'
								strokeWidth='2'
								d='M4 6h16M4 12h16M4 18h7'
							/>
						</svg>
					</div>
					<ul className='menu menu-md dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52'>
						{links.map((link) => (
							<li key={link.id}>
								<NavLink
									style={({ isActive }) =>
										isActive ? { display: "none" } : {}
									}
									to={link.path}
									end
								>
									{link.name}
								</NavLink>
							</li>
						))}
					</ul>
				</div>
			</div>
			<div className='navbar-center'>
				<a className='btn btn-ghost text-xl'>{LOGO}</a>
			</div>
			<div className='navbar-end gap-2'>
				{loggedIn && (
					<Form method='post'>
						<button className='btn btn-link'>Sign out</button>
					</Form>
				)}

				<select
					className='select select-md focus:outline-none select-ghost max-w-xs text-md'
					onChange={changeTheme}
				>
					<option disabled>Theme</option>
					{THEMES.map((item) => (
						<option key={item} value={item}>
							{item}
						</option>
					))}
				</select>
			</div>
		</header>
	);
};

export default Header;
