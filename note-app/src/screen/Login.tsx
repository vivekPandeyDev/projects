import {
	ActionFunctionArgs,
	Form,
	redirect,
	useActionData,
	useNavigation,
} from "react-router-dom";
import { supabase } from "../service/client";
import { AuthError } from "../types";
import { useState } from "react";
import { toast } from "react-toastify";

// eslint-disable-next-line react-refresh/only-export-components
export async function action({ request }: ActionFunctionArgs) {
	console.log("action called");

	const data = await request.formData();
	const email = data.get("email") as string;
	const password = data.get("password") as string;
	const isSignIn = data.get("isSignIn") as string;

	console.log("isSignIn", isSignIn);

	const authError: AuthError = {};
	if (!email) {
		authError.email = "Enter your email address";
		return authError;
	}
	if (!password) {
		authError.password = "Enter your password";
		return authError;
	}
	if (isSignIn === "true") {
		await signInWithPassword(authError, email, password);
	} else {
		await signUpWithPassword(authError, email, password);
	}
	console.log(authError);

	return authError;
}

async function signUpWithPassword(
	authError: AuthError,
	email: string,
	password: string
) {
	const { data: authData, error } = await supabase.auth.signUp({
		email: email,
		password: password,
	});
	// checking for authentication error
	authError.message = error?.message;

	if (!error) {
		toast.success("successfull signup");
		toast.info(`confirm your email ${authData.user?.email}`);
	}
}

async function signInWithPassword(
	authError: AuthError,
	email: string,
	password: string
) {
	// if email and password are filled
	const { data: authData, error } = await supabase.auth.signInWithPassword({
		email: email,
		password: password,
	});

	// checking for authentication error
	authError.message = error?.message;

	console.log(authData);

	if (authData.session) {
		console.log(authData.session);
		toast.success("login successfully");
		throw redirect("/");
	}
}

const Login = () => {
	const error = useActionData() as AuthError;
	const { state } = useNavigation();
	const [isSignIn, setIsSignIn] = useState(true);

	return (
		<div className='min-h-[50rem] grid  place-items-center'>
			<div className='bg-white p-8 rounded shadow-md w-96'>
				<h2 className='text-3xl font-semibold mb-6 text-primary text-center '>
					Login
				</h2>
				{error?.message && (
					<span className='text-error uppercase'>
						*{error.message}
					</span>
				)}
				{/* Login Form */}
				<Form method='post'>
					<div className='mb-4'>
						<label
							htmlFor='email'
							className='block text-primary  font-bold mb-2'
						>
							email
						</label>
						<input
							type='email'
							id='email'
							name='email'
							className='w-full p-2 border rounded-md'
						/>
						{error?.email && (
							<span className='text-error p-2'>
								*{error.email}
							</span>
						)}
					</div>

					<div className='mb-4'>
						<label
							htmlFor='password'
							className='block text-primary font-bold mb-2'
						>
							Password
						</label>
						<input
							type='password'
							id='password'
							name='password'
							className='w-full p-2 border rounded-md'
						/>
						{error?.password && (
							<span className='text-error p-2'>
								*{error.password}
							</span>
						)}
					</div>

					<input
						type='text'
						name='isSignIn'
						className='w-full p-2 border rounded-md'
						value={isSignIn === true ? "true" : "false"}
						hidden
						readOnly
					/>

					<button
						type='submit'
						className='w-full bg-accent p-2 rounded-md btn hover:bg-primary'
						disabled={state === "submitting"}
						onClick={() => setIsSignIn(true)}
					>
						Login
					</button>
					<button
						className='w-full bg-primary text-white p-2 rounded-md  btn mt-2 hover:bg-secondary'
						disabled={state === "submitting"}
						onClick={() => setIsSignIn(false)}
					>
						Signup
					</button>
				</Form>
			</div>
		</div>
	);
};

export default Login;
