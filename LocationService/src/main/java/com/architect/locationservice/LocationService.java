package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
//import java.sql.Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Contains the business logic for querying the database
 */
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    private final LocationDAO locationDAO;


    private final Connection conn;

    @Autowired
    public LocationService(LocationDAO locationDAO, Connection conn) {
        this.locationDAO = locationDAO;
        this.conn = conn;
    }


    public List<Location> getNearbyLocations(Double latitude, Double longitude) {
        // Implement algorithm to retrieve nearby locations based on the user's current location
        double radius = 0.100;
        List<Location> nearbyLocations = new ArrayList<>();

        for (Location location : locationRepository.findAll()) {
            double distance = calculateDistance(latitude, longitude, location.getLatitude(), location.getLongitude());

            if (distance <= radius) {
                nearbyLocations.add(location);
            }
        }

        return nearbyLocations;
    }

    private double calculateDistance(Double lat1, Double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // kilometers

        double lat1Radians = Math.toRadians(lat1.doubleValue());
        double lon1Radians = Math.toRadians(lon1.doubleValue());
        double lat2Radians = Math.toRadians((new BigDecimal(lat2)).doubleValue());
        double lon2Radians = Math.toRadians((new BigDecimal(lon2)).doubleValue());

        double deltaLat = lat2Radians - lat1Radians;
        double deltaLon = lon2Radians - lon1Radians;

        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    public Location findById(int location_id) throws SQLException {
        return locationDAO.getLocationById(location_id);
    }


}
