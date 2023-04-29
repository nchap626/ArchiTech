package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/visitedlocations")
public class VisitedLocationController {

    //@Autowired
    //private VisitedLocationDAO visitedLocationDAO;

    @Autowired
    private VisitedLocationService visitedLocationService;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addVisitedLocation(@RequestParam int userId, @RequestParam int locationId,
                              @RequestParam String dateVisited) throws SQLException {
        LocalDateTime dateTime = LocalDateTime.parse(dateVisited);
        visitedLocationService.addVisitedLocationIfWithinDistance(userId, locationId, dateTime);
        return "Visited location added successfully";
    }

    @GetMapping(path = "/getbyid")
    public @ResponseBody
    List<VisitedLocation> getVisitedLocationsByUserId(@RequestParam int userId) {
        return visitedLocationService.getVisitedLocationsByUserId(userId);
    }

    @GetMapping(path = "/getbylocationid")
    public @ResponseBody
    List<VisitedLocation> getVisitedLocationsByLocationId(@RequestParam int locationId) {
        return visitedLocationService.getVisitedLocationsByLocationId(locationId);
    }

    @GetMapping(path = "/getbydatevisited")
    public @ResponseBody
    List<VisitedLocation> getVisitedLocationsByDateVisited(@RequestParam String dateVisited) {
        LocalDateTime dateTime = LocalDateTime.parse(dateVisited);
        return visitedLocationService.getVisitedLocationsByDateVisited(dateTime);
    }

    @DeleteMapping(path = "/deletebyid")
    public @ResponseBody
    String deleteVisitedLocationById(@RequestParam int visitedLocationId) {
        visitedLocationService.deleteVisitedLocationById(visitedLocationId);
        return "Visited location deleted successfully";
    }

    @DeleteMapping(path = "/deletebyuserid")
    public @ResponseBody
    String deleteVisitedLocationsByUserId(@RequestParam int userId) {
        visitedLocationService.deleteVisitedLocationsByUserId(userId);
        return "Visited locations deleted successfully";
    }

    @DeleteMapping(path = "/deletebylocationid")
    public @ResponseBody
    String deleteVisitedLocationsByLocationId(@RequestParam int locationId) {
        visitedLocationService.deleteVisitedLocationsByLocationId(locationId);
        return "Visited locations deleted successfully";
    }

    @DeleteMapping(path = "/deletebydatevisited")
    public @ResponseBody
    String deleteVisitedLocationsByDateVisited(@RequestParam String dateVisited) {
        LocalDateTime dateTime = LocalDateTime.parse(dateVisited);
        visitedLocationService.deleteVisitedLocationsByDateVisited(dateTime);
        return "Visited locations deleted successfully";
    }

}