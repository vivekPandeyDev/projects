import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";

import "./server.js";
import Vans from "./pages/Vans/Vans.js";
import VanDetail from "./pages/Vans/VanDetail.js";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchVansAsync } from "./features/vans/vansAction.js";
import { AppDispatch, RootState } from "./store";
import Layout from "./component/Layout.js";
import Dashboard from "./pages/Host/Dashboard.js";
import Income from "./pages/Host/Income.js";
import Review from "./pages/Host/Review.js";
import HostLayout from "./component/HostLayout.js";
import HostVans from "./pages/Host/HostVans.js";
import HostVanDetail from "./pages/Host/HostVanDetail.js";
import HostVanInfo from "./pages/Host/HostVanInfo.js";
import HostVanPricing from "./pages/Host/HostVanPricing.js";
import HostVanPhoto from "./pages/Host/HostVanPhoto.js";
import NotFoundPage from "./pages/NotFoundPage.js";
import LoginPage from "./pages/LoginPage.js";
import ProtectedRoute from "./component/ProtectedRoute.js";

function App() {
  const vansState = useSelector((state: RootState) => state.vans);
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    dispatch(fetchVansAsync());
  }, [dispatch]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route path='*' element={<NotFoundPage />} />
          {/* home page */}
          <Route index element={<Home />} />
          {/* about page */}
          <Route path='about' element={<About />} />
          {/* van route */}
          <Route path='vans' element={<Vans vansState={vansState} />} />
          <Route path='vans/:id' element={<VanDetail />} />
          {/* host route */}

          <Route path='host' element={<ProtectedRoute />}>
            <Route index element={<Dashboard />} />
            <Route path='income' element={<Income />} />
            <Route path='vans' element={<HostVans />} />
            <Route path='vans/:id' element={<HostVanDetail />}>
              <Route index element={<HostVanInfo />} />
              <Route path='pricing' element={<HostVanPricing />} />
              <Route path='photos' element={<HostVanPhoto />} />
            </Route>
            <Route path='reviews' element={<Review />} />R
          </Route>
          {/* login route */}
          <Route path='login' element={<LoginPage />}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
