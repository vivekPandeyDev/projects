import { useMutation, useQueryClient } from "@tanstack/react-query";
import { createNote, updateNote } from "../service/http";
import { NoteType } from "../types";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { intialState } from "../screen/Note";

const useMutateNote = (
	setFormData: React.Dispatch<React.SetStateAction<NoteType>>
) => {
	const queryClient = useQueryClient();
	const navigate = useNavigate();
	const { mutate: createMutation, isPending: createMutationPending } =
		useMutation({
			mutationFn: (data: NoteType) => createNote(data),
			onSuccess: async () => {
				toast.success("note has been been added");
				setFormData(intialState);
				await queryClient.invalidateQueries({
					queryKey: ["notes"],
					exact: false,
				});
				navigate(-1);
			},
			onError: () => {
				toast.error("some error occurred by updating notes");
			},
		});
	const { mutate: updateMutation, isPending: updateMutationPending } =
		useMutation({
			mutationFn: (data: NoteType) => updateNote(data.id, data),
			onSuccess: async () => {
				toast.success("note has been been updated");
				setFormData(intialState);
				await queryClient.invalidateQueries({
					queryKey: ["notes"],
					exact: false,
				});
				navigate(-1);
			},
			onError: () => {
				toast.error("some error occurred by updating notes");
			},
		});

	const isPending = createMutationPending || updateMutationPending;

	return { createMutation, updateMutation, isPending };
};

export default useMutateNote;
