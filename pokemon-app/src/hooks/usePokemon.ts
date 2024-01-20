import useSWR from "swr";
import { getPokemon } from "../network/pokemonApi";

import { PokemonSchema } from "../types/pokemon";

export default function usePokemon(name: string) {
	const { data, isLoading, mutate, error } = useSWR(name, async () =>
		getPokemon(name)
	);
	console.log(`Api data for: ${name} `, data);
	const pokemon = PokemonSchema.parse(data);
	return {
		pokemon: pokemon,
		pokemonLoading: isLoading,
		mutatePokemon: mutate,
		error: error,
	};
}
