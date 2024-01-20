import { useNavigate } from "react-router-dom";

type Props = {
	name: string;
	url: string;
};

export default function PokemonCard({ name, url }: Props) {
	const navigate = useNavigate();
	return (
		<div
			className='mx-4 bg-gradient-to-r from-blue-400 to-blue-400 shadow-md rounded-lg overflow-hidden'
			onClick={() => {
				navigate(`/${name}`);
			}}
		>
			<h2 className='text-center font-bold text-4xl sm:text-2xl uppercase pt-3'>
				{name}
			</h2>
			<figure className='mb-2 w-full h-full'>
				<img src={url} alt={name} loading='lazy'></img>
			</figure>
		</div>
	);
}
