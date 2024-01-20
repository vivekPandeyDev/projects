import { useNavigate } from "react-router-dom";
import HostLayout from "./HostLayout";
import { useEffect } from "react";

const ProtectedRoute = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const user = localStorage.getItem("user");
    if (user === null) {
      navigate("/login");
    }
  });

  return <HostLayout />;
};

export default ProtectedRoute;
