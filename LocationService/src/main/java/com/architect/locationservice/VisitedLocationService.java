package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.List;


@Service
public class VisitedLocationService {

    //private final VisitedLocationRepository visitedLocationRepository;

    private final VisitedLocationDAO visitedLocationDAO;


    private final Connection conn;


    private final UserService userService;
    private final LocationService locationService;


    private final double DISTANCE_THRESHOLD = 30.0; // in meters

    @Autowired
    public VisitedLocationService(VisitedLocationDAO visitedLocationDAO, Connection conn, UserService userService, LocationService locationService) {
        this.visitedLocationDAO = visitedLocationDAO;
        this.conn = conn;
        this.userService = userService;
        this.locationService = locationService;
    }

    public VisitedLocation addVisitedLocation(int userId, int locationId, LocalDateTime dateVisited) {
        VisitedLocation visitedLocation = new VisitedLocation();
        visitedLocation.setUserId(userId);
        visitedLocation.setLocationId(locationId);
        visitedLocation.setDateVisited(dateVisited);
        visitedLocationDAO.addVisitedLocation(userId, userId, dateVisited);
        return visitedLocation;
    }

    public List<VisitedLocation> getVisitedLocationsByUserId(int userId) {
        return visitedLocationDAO.getVisitedLocationsByUserId(userId);
    }

    public List<VisitedLocation> getVisitedLocationsByLocationId(int locationId) {
        return visitedLocationDAO.getVisitedLocationsByLocationId(locationId);
    }

    public List<VisitedLocation> getVisitedLocationsByDateVisited(LocalDateTime dateVisited) {
        return visitedLocationDAO.getVisitedLocationsByDateVisited(dateVisited);
    }

    public void deleteVisitedLocationById(int visitedLocationId) {
        visitedLocationDAO.deleteVisitedLocationById(visitedLocationId);
    }

    public void deleteVisitedLocationsByUserId(int userId) {
        visitedLocationDAO.deleteVisitedLocationsByUserId(userId);
    }

    public void deleteVisitedLocationsByLocationId(int locationId) {
        visitedLocationDAO.deleteVisitedLocationsByLocationId(locationId);
    }

    public void deleteVisitedLocationsByDateVisited(LocalDateTime dateVisited) {
        visitedLocationDAO.deleteVisitedLocationsByDateVisited(dateVisited);
    }

    public void addVisitedLocationIfWithinDistance(int userId, int locationId, LocalDateTime dateVisited) throws SQLException {
        User user = userService.getUserByID(userId);
        Location location = locationService.findById(locationId);
        VisitedLocation visitedLocation = new VisitedLocation();
        visitedLocation.setUserId(userId);
        visitedLocation.setLocationId(locationId);
        visitedLocation.setDateVisited(dateVisited);
        double distance = getDistance(user.getLatitude(), user.getLongitude(),
                location.getLatitude(), location.getLongitude());
        if (distance <= DISTANCE_THRESHOLD && !(getVisitedLocationsByUserId(userId).contains(visitedLocation) && getVisitedLocationsByLocationId(locationId).contains(visitedLocation))) {
            visitedLocationDAO.addVisitedLocation(userId, userId, dateVisited);
        }
    }

    private double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double lat1 = Math.toRadians(latitude1);
        double lon1 = Math.toRadians(longitude1);
        double lat2 = Math.toRadians(latitude2);
        double lon2 = Math.toRadians(longitude2);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = 6371 * c;
        return distance * 1000;
    }



}
