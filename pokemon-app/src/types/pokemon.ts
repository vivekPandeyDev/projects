import { z } from "zod";

export interface PokemonPage {
	results: { name: string; url: string }[];
	next: string | null;
	previous: string | null;
}

export interface PokemonResult {
	name: string;
	url: string;
}

export const PokemonSchema = z.object({
	name: z.string(),
	types: z.array(
		z.object({
			type: z.object({
				name: z.string(),
			}),
		})
	),
	weight: z.number(),
	height: z.number(),
	sprites: z.object({
		other: z.object({
			"official-artwork": z.object({
				front_default: z.string(),
			}),
		}),
	}),
});

export type Pokemon = z.infer<typeof PokemonSchema>;
