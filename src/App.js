import React from "react";
import Map from "./Map";
import LandingPage from "./LandingPage";
import Location from "./Location";
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
function App() {
  return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path = "/" element = {<LandingPage/>}/>
                    <Route path={"/map"} element={<Map/>}/>
                    <Route path={"/locations"} element={<Location/>}/>
                </Routes>
            </BrowserRouter>
        </div>
  )
}

export default App;
