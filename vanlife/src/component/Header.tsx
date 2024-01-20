import { NavLink } from "react-router-dom";

const Header = () => {
  const activeStyles = {
    fontWeight: "bold",
    textDecoration: "underline",
    color: "#161616",
  };
  return (
    <header>
      <div>
        <NavLink className='site-logo' to='.'>
          #VanLife
        </NavLink>
      </div>
      <nav>
        <NavLink
          style={({ isActive }) => (isActive ? activeStyles : undefined)}
          to='about'
        >
          About
        </NavLink>
        <NavLink
          style={({ isActive }) => (isActive ? activeStyles : undefined)}
          to='host'
        >
          Host
        </NavLink>
        <NavLink
          style={({ isActive }) => (isActive ? activeStyles : undefined)}
          to='vans'
        >
          Vans
        </NavLink>
        {/* <NavLink
          style={({ isActive }) => (isActive ? activeStyles : undefined)}
          to='login'
        >
          Login
        </NavLink> */}
      </nav>
    </header>
  );
};

export default Header;
