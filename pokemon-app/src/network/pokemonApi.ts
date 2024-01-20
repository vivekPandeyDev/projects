import { Pokemon, PokemonPage } from "../types/pokemon";
import api from "./axiosInstance";

export async function getPokemon(name: string) {
	const delay = Math.random() * 2000;
	await new Promise((r) => setTimeout(r, delay));
	const response = await api.get<Pokemon>("/pokemon/" + name);
	return response.data;
}

export async function getPokemonByName(names: string[]) {
	const delay = Math.random() * 2000;
	await new Promise((r) => setTimeout(r, delay));

	const responseAll = await Promise.all(
		names.map((name) => api.get<Pokemon>("/pokemon/" + name))
	);

	return responseAll.map((response) => response.data);
}

export async function getPokemonPage(page: number, pageSize = 12) {
	const response = await api.get<PokemonPage>(
		`/pokemon?limit=${pageSize}&offset=${pageSize * (page - 1)}`
	);
	return response.data;
}

export async function setNickname(pokemon: Pokemon, nickname: string) {
	return { ...pokemon, name: nickname };
}
