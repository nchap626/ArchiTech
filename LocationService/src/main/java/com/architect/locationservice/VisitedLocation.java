package com.architect.locationservice;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Visited_Locations")
public class VisitedLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "date_visited")
    private LocalDateTime dateVisited;

    public VisitedLocation() {}

    public VisitedLocation(int userId, int locationId, LocalDateTime dateVisited) {
        this.userId = userId;
        this.locationId = locationId;
        this.dateVisited = dateVisited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(LocalDateTime dateVisited) {
        this.dateVisited = dateVisited;
    }
}