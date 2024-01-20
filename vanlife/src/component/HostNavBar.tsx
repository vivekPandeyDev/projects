import { NavLink } from "react-router-dom";

const HostNavBar = () => {
  const activeStyles = {
    fontWeight: "bold",
    textDecoration: "underline",
    color: "#161616",
  };
  return (
    <nav className='host-nav'>
      <NavLink
        to='.'
        end
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
      >
        Dashboard
      </NavLink>
      <NavLink
        to='income'
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
      >
        Income
      </NavLink>
      <NavLink
        to='vans'
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
      >
        Vans
      </NavLink>
      <NavLink
        to='reviews'
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
      >
        Reviews
      </NavLink>
    </nav>
  );
};

export default HostNavBar;
