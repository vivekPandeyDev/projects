import { Link, useSearchParams } from "react-router-dom";
import { VansProps } from "../../interface/props";

const Vans = ({ vansState }: VansProps) => {
  const { error, status, vans } = vansState;
  console.log("====================================");
  console.log("vans state", { error, status, vans });
  console.log("====================================");

  const [searchParams, setSearchParams] = useSearchParams();

  const typeFilter = searchParams.get("type");

  const displayedVans = typeFilter
    ? vans.filter((van) => van.type === typeFilter)
    : vans;

  const vanElements = displayedVans.map((van) => (
    <div key={van.id} className='van-tile'>
      <Link key={van.id} to={`${van.id}`} state={{ searchParams: typeFilter }}>
        <img src={van.imageUrl} />
        <div className='van-info'>
          <h3>{van.name}</h3>
          <p>
            ${van.price}
            <span>/day</span>
          </p>
        </div>
        <i className={`van-type ${van.type} selected`}>{van.type}</i>
      </Link>
    </div>
  ));

  const vanFilters = [...new Set(vans.map((van) => van.type))].map((type) => (
    <button
      onClick={() => handleFilterChange("type", type)}
      className={`van-type ${type} ${typeFilter === type ? "selected" : ""}`}
    >
      {type}
    </button>
  ));

  function handleFilterChange(key: string, value: string) {
    setSearchParams((prevParams) => {
      if (value === "") {
        prevParams.delete(key);
      } else {
        prevParams.set(key, value);
      }
      return prevParams;
    });
  }

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

  return (
    <main className='van-list-container'>
      <h1>Explore our van options</h1>
      <div className='van-list-filter-buttons'>
        {vanFilters}
        {typeFilter ? (
          <button
            onClick={() => handleFilterChange("type", "")}
            className='van-type clear-filters'
          >
            Clear filter
          </button>
        ) : null}
      </div>
      <div className='van-list'>{vanElements}</div>
    </main>
  );
};

export default Vans;
