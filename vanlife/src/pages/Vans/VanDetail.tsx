import { useSelector } from "react-redux";
import { Link, useLocation, useParams } from "react-router-dom";
import { RootState } from "../../store";

export default function VanDetail() {
  const params = useParams();
  const {state }= useLocation();

  const id: number = params.id
    ? Number.isNaN(+params.id)
      ? 1
      : +params.id
    : 1;

  const van = useSelector((state: RootState) => state.vans.vans[id - 1]);

  console.log(state);

  return (
    <main>
      <Link to={state.searchParams == null ?'..' : `..?type=${state.searchParams}`} relative='path' className='back-button'>
        &larr; <span>Back to all vans</span>
      </Link>
      <div className='van-detail-container'>
        {van ? (
          <div className='van-detail'>
            <img src={van.imageUrl} />
            <i className={`van-type ${van.type} selected`}>{van.type}</i>
            <h2>{van.name}</h2>
            <p className='van-price'>
              <span>${van.price}</span>/day
            </p>
            <p>{van.description}</p>
            <button className='link-button'>Rent this van</button>
          </div>
        ) : (
          <h2>Loading...</h2>
        )}
      </div>
    </main>
  );
}
