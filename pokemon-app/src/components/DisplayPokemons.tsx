import PokemonCard from "./PokemonCard";
import { PokemonResult } from "../types/pokemon";
import useSWR from "swr";
import { getPokemonByName } from "../network/pokemonApi";
import LoadingCard from "./LoadingCard";

interface Props {
	results: PokemonResult[];
}

const DisplayPokemons = ({ results }: Props) => {
	const names = results.map((result) => result.name);
	const {
		data: pokemons,
		error,
		isLoading,
	} = useSWR(names ? names : null, async (names) => getPokemonByName(names));
	if (isLoading) {
		return (
			<div className='flex justify-center flex-wrap gap-7 mt-5 p-5'>
				{[...Array(12).keys()].map((index) => (
					<LoadingCard key={index} />
				))}
			</div>
		);
	}

	if (error) {
		return <div>Error occured</div>;
	}

	return (
		<div className='flex justify-center flex-wrap gap-7 mt-5  sm:grid sm:grid-cols-3 md:grid-cols-4'>
			{pokemons?.map((pokemon) => (
				<PokemonCard
					name={pokemon.name}
					key={pokemon.name}
					url={
						pokemon.sprites.other["official-artwork"].front_default
					}
				/>
			))}
		</div>
	);
};

export default DisplayPokemons;
