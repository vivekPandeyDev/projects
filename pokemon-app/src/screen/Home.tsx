import { useState } from "react";
import DisplayPokemons from "../components/DisplayPokemons";
import useSWR from "swr";
import { getPokemonPage } from "../network/pokemonApi";
import { AxiosError } from "axios";
import LoadingCard from "../components/LoadingCard";

const pageSize = 12;

export default function Home() {
	const [page, setPage] = useState(1);
	const {
		data: pokemonResponse,
		error,
		isLoading,
	} = useSWR(
		`/pokemon?limit=${pageSize}&offset=${pageSize * (page - 1)}`,
		async () => getPokemonPage(page)
	);

	if (isLoading) {
		return (
			<div className='container mx-auto  min-h-screen'>
				<h1 className='text-center text-4xl sm:text-6xl p-2 mb-4 font-bold'>
					Gotta catch`em all
				</h1>
				<div className='flex justify-center flex-wrap gap-7 mt-5 p-5'>
					{[...Array(12).keys()].map((index) => (
						<LoadingCard key={index} />
					))}
				</div>
			</div>
		);
	}

	if (error) {
		if (error instanceof AxiosError)
			return <div>Error while connecting to api {error.message}</div>;
		else return <div>Something went wrong</div>;
	}
	if (!pokemonResponse) {
		return <div>No pokemon found</div>;
	}
	return (
		<div className='container mx-auto  min-h-screen'>
			<h1 className='text-center text-4xl sm:text-6xl p-2 mb-4 font-bold'>
				Gotta catch`em all
			</h1>
			<DisplayPokemons results={pokemonResponse.results} />
			<div className='flex justify-center flex-wrap gap-7 mt-5'>
				{pokemonResponse.previous && (
					<button
						className='text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-lg  py-2.5 me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 focus:outline-none dark:focus:ring-red-800 w-1/3 '
						onClick={() => setPage((prev) => prev - 1)}
					>
						prev
					</button>
				)}

				{pokemonResponse.next && (
					<button
						type='button'
						className='text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-8 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800 w-1/3 '
						onClick={() => setPage((prev) => prev + 1)}
					>
						Next
					</button>
				)}
			</div>
		</div>
	);
}
