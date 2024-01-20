import { Link, useParams } from "react-router-dom";
import useSWR from "swr";
import { getPokemon } from "../network/pokemonApi";

const Pokemon = () => {
	const { name } = useParams();
	const {
		data: pokemon,
		error,
		isLoading,
	} = useSWR(name || null, async (name) => getPokemon(name));
	if (!name) {
		return <div>Not a valid param</div>;
	}

	let pokemonType = "";

	if (isLoading) {
		<div>Loading...</div>;
	}
	if (error) {
		return <div>err</div>;
	}
	if (!pokemon) {
		return <div>NO pokemon found</div>;
	}
	pokemon.types.map((data) => (pokemonType += data.type.name + ","));
	return (
		<div className='min-h-screen flex items-center flex-col'>
			<Link
				to='..'
				relative='path'
				className='mt-2 font-extrabold text-white text-2xl'
			>
				<i className='fa fa-arrow-lefts'>&#xf060;</i> Poke`Dex
			</Link>
			<figure className='mt-2'>
				<img
					src={
						pokemon.sprites.other["official-artwork"].front_default
					}
				></img>
			</figure>
			<div className='font-bold text-2xl'>
				<h3>
					Type :{" "}
					<span className='text-white  mx-2 uppercase'>
						{pokemonType.slice(0, -1)}
					</span>
				</h3>
				<h3>
					Weight :
					<span className='text-white mx-2'>{pokemon.weight}</span>
				</h3>
				<h3>
					Height :{" "}
					<span className='text-white  mx-2'>{pokemon.height}</span>
				</h3>
			</div>
			<div className='mt-2 flex flex-col gap-3'>
				<h1 className='text-2xl text-center font-bold '>
					Give this pokemon a nickname
				</h1>
				<input
					type='text'
					name='name'
					placeholder='give a nickname 🤔'
					className='bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 text-lg'
				/>
				<button className='uppercase font-bold text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300  rounded-lg text-sm px-8 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800  '>
					set nickname
				</button>
			</div>
		</div>
	);
};

export default Pokemon;
