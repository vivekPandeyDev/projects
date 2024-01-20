import { useQuery } from "@tanstack/react-query";
import { FormEvent, useEffect, useState } from "react";
import { getAllCategory } from "../service/http";
import { useLocation } from "react-router-dom";
import useMutateNote from "../hooks/useMutateNote";
import { NoteType } from "../types";

// eslint-disable-next-line react-refresh/only-export-components
export const intialState: NoteType = {
	id: 0,
	title: "",
	content: "",
	category: 1,
	created_at: "",
	updated_at: "",
};

const Note = () => {
	const { data: categories } = useQuery({
		queryKey: ["category"],
		queryFn: () => getAllCategory(),
		staleTime: Infinity,
	});
	const location = useLocation();
	const [formData, setFormData] = useState(intialState);
	const { createMutation, updateMutation, isPending } =
		useMutateNote(setFormData);

	function handleSubmit(e: FormEvent<HTMLFormElement>) {
		e.preventDefault();
		const data = {
			...formData,
			created_at: new Date().toDateString(),
		};
		if (location.state?.type === "edit") {
			updateMutation({ ...data, id: location.state.id });
		} else {
			createMutation(data);
		}
	}

	useEffect(() => {
		if (location.state?.type === "edit") {
			const note = location.state.note as NoteType;
			setFormData({ ...note });
		}
	}, [location.state?.note, location.state?.type]);

	return (
		<main className='grid  place-items-center gap-4 my-4 '>
			<div className='card max-w-2xl w-[45rem] bg-base-100 shadow-2xl border '>
				<div className='card-body text-center'>
					<h2 className='card-title text-info justify-center'>
						{location.state?.type === "edit" ? "Update" : "Add"} New
						Notes
					</h2>
					<form onSubmit={handleSubmit}>
						<div className='join'>
							<div>
								<div>
									<input
										className='input input-bordered join-item'
										placeholder='Note title'
										onChange={(e) =>
											setFormData((prev) => ({
												...prev,
												title: e.target.value,
											}))
										}
										value={formData.title}
										required
									/>
								</div>
							</div>
							<select
								className='select select-bordered join-item text-lg'
								value={formData.category}
								onChange={(e) =>
									setFormData((prev) => ({
										...prev,
										category: +e.target.value,
									}))
								}
								required
							>
								{categories &&
									categories.map((item) => (
										<option key={item.id} value={item.id}>
											{item.category_name}
										</option>
									))}
							</select>
							<div className='indicator'>
								<span className='indicator-item badge badge-secondary'>
									{categories?.find(
										(item) => formData.category === item.id
									)?.priority || "medium"}
								</span>
								<button disabled className='btn join-item'>
									Priority
								</button>
							</div>
						</div>

						<div className='mt-2'>
							<textarea
								className='textarea focus:outline-none w-full textarea-info  textarea-lg'
								placeholder='type your note content'
								onChange={(e) =>
									setFormData((prev) => ({
										...prev,
										content: e.target.value,
									}))
								}
								value={formData.content}
								required
							></textarea>
						</div>
						<button
							className='btn btn-accent w-full'
							disabled={isPending}
						>
							{isPending ? (
								<span className='loading loading-spinner loading-lg'></span>
							) : (
								<span>submit</span>
							)}
						</button>
					</form>
				</div>
			</div>
		</main>
	);
};

export default Note;
