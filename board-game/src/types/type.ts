import { LoaderFunction } from "react-router-dom";

export type GameLevel = "easy" | "medium" | "hard";

export type Contact = {
	id: number;
	firstName: string;
	lastName: string;
	avatar?: string;
	twitter: string;
	notes: string;
	favorite: boolean;
};

type MyParam = {
	id: string;
};

export type ActionType<T = MyParam> = {
	request: Request;
	params: T;
};

export type LoaderData<TLoaderFn extends LoaderFunction> = Awaited<
	ReturnType<TLoaderFn>
> extends Response | infer D
	? D
	: never;
