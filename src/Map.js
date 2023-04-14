import mapboxgl from "mapbox-gl";
import React, { useEffect, useRef } from "react";
import ReactDOM from "react-dom";
import geoJson from "./tech-locations.json";
import "./Map.css";
import * as turf from "@turf/turf"

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
      console.log(currentMarkers)
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

          //if point in distance, render it
          if (distance < 0.5) {
            currentMarkers[i].addTo(map)
          } else {
            currentMarkers[i].remove()
          };
        } else {
          currentMarkers[i].addTo(map)
        }; 

      }; 

    }, 5000); 



    // Clean up on unmount
    return () => map.remove();
  });

  const markerClicked = (feature) => {
    new mapboxgl.Popup(mapRef.current)
      .setLngLat(feature.geometry.coordinates)
      .setHTML(feature.properties.description)
      .addTo(mapRef.current);
  };

  return <div className="map-container" ref={mapContainerRef} />;
};

export default Map;
