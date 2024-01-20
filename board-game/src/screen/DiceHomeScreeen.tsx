import { useNavigate } from "react-router-dom";
import logo from "../assets/images/dices.png";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

const DiceHomeScreeen = () => {
	const [loading, setLoading] = useState(false);
	const [level, setLevel] = useState("");
	const navigate = useNavigate();

	function toGameScreen() {
		if (level.length !== 0) {
			toast("Game Starting", {
				position: "top-right",
				isLoading: true,
				delay: 0,
			});
			setLoading(true);
			setTimeout(() => {
				setLoading(false);
				navigate("/play", { state: { level: level } });
			}, 1000);
		} else {
			toast.info("Please select game level");
		}
	}
	return (
		<div
			id='game-menu'
			className='flex items-center justify-center gap-3 min-h-screen'
		>
			<div className='flex-grow'>
				<img src={logo} className='m-auto'></img>
			</div>
			<div className='flex-grow relative '>
				<h1 className='uppercase text-9xl font-bold'>Dice Game</h1>
				<div className='absolute end-[17rem] p-2 flex gap-5'>
					<select
						className='select w-full max-w-xs'
						onChange={(e) => setLevel(e.target.value)}
					>
						<option disabled selected value=''>
							Select your level
						</option>
						<option value='easy'>Easy</option>
						<option value='medium'>Medium</option>
						<option value='hard'>Hard</option>
					</select>
					<button
						className='btn btn-neutral min-w-[7rem] text-end'
						onClick={toGameScreen}
					>
						{loading ? (
							<span className='loading loading-spinner loading-lg'></span>
						) : (
							<span>Play Now</span>
						)}
					</button>
				</div>
			</div>
			<ToastContainer />
		</div>
	);
};

export default DiceHomeScreeen;
