package com.architect.locationservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/nearby")
    public List<Location> getNearbyLocations(@RequestParam Double latitude, @RequestParam Double longitude) {
        return locationService.getNearbyLocations(latitude, longitude);
    }
}