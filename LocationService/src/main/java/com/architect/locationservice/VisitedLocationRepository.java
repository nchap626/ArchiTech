package com.architect.locationservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitedLocationRepository extends JpaRepository<VisitedLocation, Long> {

    List<VisitedLocation> findByUserId(int userId);

    List<VisitedLocation> findByLocationId(int locationId);

    List<VisitedLocation>  findByDateVisited(LocalDateTime dateVisited);

    void deleteById(int visitedLocationId);

    void deleteByUser_Id(int userId);

    void deleteByLocation_Id(int locationId);

    void deleteByDateVisited(LocalDateTime dateVisited);

}
