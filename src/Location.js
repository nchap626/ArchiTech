import React from 'react';
import "./LocationStyles.css";
import DateTime from './DateTime';
import { useNavigate } from 'react-router-dom';

function Location() {

    let navigate = useNavigate();
    const navigateToHome = () =>{ 
        let path = `/`; 
        navigate(path);
    }

    const navigateToMap = () =>{ 
        let path = `/map`; 
        navigate(path);
    }

    const seeLocations = () => {
        var localVisited = localStorage.getItem("visitedList")
        var visited = JSON.stringify(localVisited)
        visited = visited.substring(visited.indexOf("[") + 3, visited.indexOf("]") - 1)
        visited = visited.split("\"")
        let newVisited = ""
        for (let place in visited) {
            if (visited[place].includes(",")) {
                continue
            }
            visited[place] = visited[place].substring(0, visited[place].length - 1)
            newVisited += visited[place] + "<br>"
        }
        localStorage.visited = newVisited
        document.getElementById("result").innerHTML = localStorage.visited;
    }   
    
    return (
        <div className='location-page'>
            <div className="locparent">
                <div className="date">
                    <DateTime></DateTime>
                </div>
                <div className="img">
                    <img src='building.png' width={100} height={80} alt='logo'></img>
                </div>
                <div className="loctitle">
                    <h1 style={{fontSize: 100, color:"navy blue"}}>Archi</h1>
                    <h1 style={{fontSize: 100, color:"white"}}>Tech</h1>
                </div>
            </div>
            <div className='locparent2'>
                <button class="locbutton" onClick={navigateToMap}>Back To Map</button>
                <button class="locbutton1" onClick={navigateToHome}>Back To Home</button>
                <button class="locbutton2" onClick={seeLocations}>See Locations</button>
            </div>
            <br></br>
            <div className='locations'>
                <h1 id='result'></h1>
            </div>
            <small><center>ArchiTech was brought to you by Summer Sneed, Alma Nkemla, Nick Chapman, and Samuel Sanders.</center></small>
        </div>
    );
}
export default Location;