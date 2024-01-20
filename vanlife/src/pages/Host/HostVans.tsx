import { Link } from "react-router-dom";
import { AppDispatch, RootState } from "../../store";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { fetchHostVansAsync } from "../../features/host/hostAction";

const HostVans = () => {
  const { vans, error, status } = useSelector((state: RootState) => state.host);

  const dispatch = useDispatch<AppDispatch>();


  useEffect(() => {
    if (vans.length == 0) dispatch(fetchHostVansAsync());
  }, [vans.length, dispatch]);

  if (status === "LOADING") {
    return <h1>Loading.....</h1>;
  }

  if (status === "ERROR") {
    return (
      <div>
        <h1>something went wrong!!!</h1>
        <p>{error}</p>
      </div>
    );
  }

  const hostVansEls = vans.map((van) => (
    <Link
      to={`${van.id}`}
      key={van.id}
      className='host-van-link-wrapper'
      
    >
      <div className='host-van-single' key={van.id}>
        <img src={van.imageUrl} alt={`Photo of ${van.name}`} />
        <div className='host-van-info'>
          <h3>{van.name}</h3>
          <p>${van.price}/day</p>
        </div>
      </div>
    </Link>
  ));

  return (
    <section>
      <h1 className='host-vans-title'>Your listed vans</h1>
      <div className='host-vans-list'>
        <section>{hostVansEls}</section>
      </div>
    </section>
  );
};

export default HostVans;
