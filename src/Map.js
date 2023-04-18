import mapboxgl from "mapbox-gl";
import React, { useEffect, useRef } from "react";
import ReactDOM from "react-dom";
import geoJson from "./tech-locations.json";
import "./Map.css";
import * as turf from "@turf/turf"
import DateTime from "./DateTime";
import { useNavigate } from "react-router-dom";

const start = new Date().getTime();

mapboxgl.accessToken =
  "pk.eyJ1IjoibmNoYXA2MjYiLCJhIjoiY2xnY3VoYTQ0MHJ2aDNobnppeW85Ym5mNiJ9.AddFfspa30LJ5D0zgfmL-w";

const Marker = ({ onClick, children, feature }) => {
  const _onClick = () => {
    onClick(feature);
  };

  return (
    <button onClick={_onClick} className="marker">
      {children}
    </button>
  );
};

var visited = [];

const Map = () => {
  const mapContainerRef = useRef(null);
  const mapRef = useRef(null);
  const positionRef = useRef(null);

  // Initialize map when component mounts
  useEffect(() => {
    const map = new mapboxgl.Map({
      container: mapContainerRef.current,
      style: "mapbox://styles/mapbox/streets-v12",
      center: [-84.3958, 33.7770],
      zoom: 15,
    });

    mapRef.current = map;

    // Add navigation control (the +/- zoom buttons)
    map.addControl(new mapboxgl.NavigationControl(), "top-right");


    //Track User Position
    var geolocate = new mapboxgl.GeolocateControl(new mapboxgl.GeolocateControl({
      positionOptions: {
        enableHighAccuracy : false
      }, 
      trackUserLocation : true
    }));

    map.addControl(geolocate, "top-left");
    geolocate.on('geolocate', function(e) {
      var lon = e.coords.longitude;
      var lat = e.coords.latitude;
      var position = [lon, lat];
      console.log(position);
      positionRef.current = position;
    }); 



    var currentMarkers = []
    var featureArr = []
    // Render custom marker components and store in above array
    geoJson.features.forEach((feature) => {

      //-----CREATING MARKER REF-----
      // Create a React ref
      const ref = React.createRef();
      // Create a new DOM node and save it to the React ref
      ref.current = document.createElement("div");
      // Render a Marker Component on our new DOM node
      ReactDOM.render(
        <Marker onClick={markerClicked} feature={feature} />,
        ref.current
      );

      // Create a Mapbox Marker at our new DOM node
      var newMarker = new mapboxgl.Marker(ref.current)
        .setLngLat(feature.geometry.coordinates);
      
      currentMarkers.push(newMarker) 
      // console.log(currentMarkers)

      featureArr.push(feature)
      // console.log(featureArr)
    });
    
    
    // reload relevant features every 2 seconds
    window.setInterval(function() {

      for (var i = currentMarkers.length - 1; i >= 0; i--) {
        // if given a local position, only show relevant points
        if (positionRef.current !=  null) {
          //calculate distance
          var from = positionRef.current;
          var to = currentMarkers[i].getLngLat().toArray();
          var options = {units: 'miles'};
          var distance = turf.distance(from, to, options);
          // console.log(distance);

          //check if a place is visited
          var isVisited = false;
          var localVisited = localStorage.getItem("visitedList")
          localVisited = JSON.parse(localVisited)

          for (var j = localVisited.length - 1; j >= 0; j--) {
            if(featureArr[i].properties.title === localVisited[j]){
              isVisited = true;
            }; 
          }

          //if distance for point is less than X, render it
          if (distance < 0.1 || isVisited) {
            currentMarkers[i].addTo(map)
          } else {
            currentMarkers[i].remove()
          };
        } else {
          currentMarkers[i].addTo(map)
        }; 

      }; 

    }, 2000); 



    // Clean up on unmount
    return () => map.remove();
  });
  var count = 0;
  const markerClicked = (feature) => {
    new mapboxgl.Popup(mapRef.current)
      .setLngLat(feature.geometry.coordinates)
      .setHTML(feature.properties.description)
      .addTo(mapRef.current);
    
    //if not in visited list, add it
    var tracked = false
    for (var k = visited.length - 1; k >= 0; k--) {
      if(feature.properties.title === visited[k]){
        tracked = true;
      }; 
    };
    
    if(!tracked){
      visited.push(feature.properties.title)
      count++
    }
    
    localStorage.setItem("visitedList", JSON.stringify(visited))
    //console.log(visited)
  };
    const timeTaken = new Date().getTime() - start;
    console.log("Map Loading Time: " + timeTaken + "ms");
  let navigate = useNavigate();
    const navigateToHome = () =>{ 
        let path = `/`; 
        navigate(path);
    }

    const navigateToLocations = () =>{ 
        let path = `/locations`; 
        navigate(path);
    }

    var localStorageSpace = function(){
      var allStrings = '';
      for(var key in window.localStorage){
          if(window.localStorage.hasOwnProperty(key)){
              allStrings += window.localStorage[key];
          }
      }
      return allStrings ? 3 + ((allStrings.length*16)/(8*1024)) + ' KB' : 'Empty (0 KB)';
  };

  console.log(localStorageSpace())
  console.log(localStorage.getItem("visitedList"))
  return (
    <div className="map page">
      <div className="top-page">
        <div className="parent">
          <div className="date">
            <DateTime></DateTime>
          </div>
          <div className="img">
            <img src='building.png' width={100} height={80} alt='logo'></img>
          </div>
          <div className="title">
            <h1 style={{fontSize: 100, color:"navy blue"}}>Archi</h1>
            <h1 style={{fontSize: 100, color:"white"}}>Tech</h1>
          </div>
        </div>
        <div className="parent2">
          <button class="button1" onClick={navigateToHome}>Back To Home</button>
          <button class="button" onClick={navigateToLocations}>Learned Locations</button>
        </div>
      </div>
      <div className="map-container" ref={mapContainerRef} />;
  </div>
  )
};

export default Map;
