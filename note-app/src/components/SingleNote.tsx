import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { NoteType } from "../types";
import { deleteNote, getCategoryById } from "../service/http";
import { FiEdit2 } from "react-icons/fi";
import { MdOutlineDelete } from "react-icons/md";
import { BiCategoryAlt } from "react-icons/bi";
import { toast } from "react-toastify";
import Skeleton from "./Skeleton";
import { useNavigate, useSearchParams } from "react-router-dom";

interface Props {
	note: NoteType;
}
const SingleNote = ({ note }: Props) => {
	const navigate = useNavigate();
	const client = useQueryClient();
	const { data: category } = useQuery({
		queryKey: [note.category],
		queryFn: () => getCategoryById(note.category),
		staleTime: Infinity,
	});
	//getting current params
	const [searchParams] = useSearchParams();
	const page = +(searchParams.get("_page") || 1);
	const limit = +(searchParams.get("_limit") || 4);
	// remove the note and revalidate the query
	const { mutate, isIdle } = useMutation({
		mutationFn: (id: number) => deleteNote(id),
		onError: () => {
			toast.error("failed to remove note");
		},
		onSuccess: () => {
			toast.success("successfully removed note");
			client.invalidateQueries({ queryKey: ["notes", { page, limit }] });
		},
	});

	if (!isIdle) {
		return <Skeleton />;
	}

	return (
		<div className='card max-w-2xl w-[45rem] bg-accent text-secondary-content '>
			<div className='card-body'>
				<h2 className='card-title'>{note.title}</h2>
				<p>{note.content}</p>
				<div className='card-actions justify-between items-baseline'>
					<span className='badge badge-success gap-2 justify-self-start p-4 font-bold'>
						<BiCategoryAlt />
						{category?.category_name || "general"}
					</span>
					<div className='card-actions justify-between items-baseline text-lg'>
						<span className='text-gray-600'>
							{note.created_at || new Date().toDateString()}
						</span>
						<span
							className='btn btn-circle text-lg btn-info'
							onClick={() =>
								navigate("/note", {
									state: {
										type: "edit",
										id: note.id,
										note: note,
									},
								})
							}
						>
							<FiEdit2 />
						</span>
						<span
							className='btn btn-circle text-lg btn-error'
							onClick={() => mutate(note.id)}
						>
							<MdOutlineDelete />
						</span>
					</div>
				</div>
			</div>
		</div>
	);
};

export default SingleNote;
