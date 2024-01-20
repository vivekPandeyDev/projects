import { useQuery } from "@tanstack/react-query";
import SingleNote from "../components/SingleNote";
import { getAllNote } from "../service/http";
import Skeleton from "../components/Skeleton";
import { useSearchParams } from "react-router-dom";
import Alert from "../components/ui/Alert";
import Input from "../components/Input";
import useDebounce from "../hooks/useDebounce";
import { useState } from "react";

function Home() {
	const [searchParams, setSearchParams] = useSearchParams();
	const [searchText, setSearchText] = useState("");
	// debounce time = {700} milliseconds
	const searchDebounceText = useDebounce(searchText, 700);

	const page = +(searchParams.get("_page") || 1);
	const limit = +(searchParams.get("_limit") || 4);
	const {
		data: notes,
		isLoading,
		isError,
		error,
	} = useQuery({
		queryKey: ["notes", { page, limit, searchText: searchDebounceText }],
		queryFn: () => getAllNote(page, limit, searchDebounceText),
		staleTime: 1000 * 60 * 5, // 5 minutes data will be fresh after that refetchs
	});
	const handleNext = () => {
		setSearchParams((prev) => ({
			...prev,
			_page: page + 1,
			_limit: limit,
		}));
	};

	const handlePrev = () => {
		setSearchParams((prev) => ({
			...prev,
			_page: Math.max(page - 1, 1),
			_limit: limit,
		}));
	};

	return (
		<main className='grid  place-items-center gap-4 mb-20'>
			<div className='flex max-w-2xl w-[45rem] gap-3 items-center'>
				<button
					className='btn btn-error'
					onClick={handlePrev}
					disabled={page === 1}
				>
					Previous
				</button>
				<button
					className='btn btn-accent'
					onClick={handleNext}
					disabled={notes && notes.length < limit}
				>
					Next Note
				</button>

				<Input setSearchText={setSearchText} />
			</div>
			{/* as state can be loading, error , success */}
			{isLoading && <Skeleton size={4} />}
			{isError && (
				<Alert type='error'>
					<span>{error.message}</span>
				</Alert>
			)}
			{/* success case but not not to display */}
			{notes && notes.length === 0 && (
				<Alert type='info'>
					<span>No Notes to display</span>
				</Alert>
			)}
			{/* success and note to display */}
			{notes &&
				notes.map((note) => <SingleNote key={note.id} note={note} />)}
		</main>
	);
}

export default Home;
