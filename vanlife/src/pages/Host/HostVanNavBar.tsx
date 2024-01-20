import { NavLink } from "react-router-dom";

const activeStyles = {
  fontWeight: "bold",
  textDecoration: "underline",
  color: "#161616",
};

const HostVanNavBar = () => {
  return (
    <nav className='host-van-detail-nav' style={{marginLeft : "28px"}}>
      <NavLink
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
        end
        to='.'
      >
        Details
      </NavLink>
      <NavLink
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
        to='pricing'
      >
        Pricing
      </NavLink>
      <NavLink
        style={({ isActive }) => (isActive ? activeStyles : undefined)}
        to='photos'
      >
        Photos
      </NavLink>
    </nav>
  );
};

export default HostVanNavBar;
