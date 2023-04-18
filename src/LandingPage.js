import React from 'react';
import './LandingPageStyles.css';
import { useNavigate } from "react-router-dom";
import DateTime from './DateTime';

function LandingPage() {
    let navigate = useNavigate();
    const navigateToMap = () =>{ 
        let path = `/map`; 
        navigate(path);
    }

    const navigateToLocations = () =>{ 
        let path = `/locations`; 
        navigate(path);
    }

    return (
        <div className='landing-page'>
            <div className="lparent">
                <div className="date">
                    <DateTime></DateTime>
                </div>
                <div className="img">
                    <img src='building.png' width={100} height={80} alt='logo'></img>
                </div>
                <div className="ltitle">
                    <h1 style={{fontSize: 100, color:"navy blue"}}>Archi</h1>
                    <h1 style={{fontSize: 100, color:"white"}}>Tech</h1>
                </div>
            </div>
            <div className='lparent2'>
                <button class="lbutton" onClick={navigateToMap}>Go To Map</button>
                <button class="lbutton1" onClick={navigateToLocations}>Go To Locations</button>
                </div>
            <small><center>ArchiTech was brought to you by Summer Sneed and Nick Chapman.</center></small>
        </div>
    );
}

export default LandingPage;