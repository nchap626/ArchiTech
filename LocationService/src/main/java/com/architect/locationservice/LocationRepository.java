package com.architect.locationservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
    Optional<Location> findById(int id);
    Optional<Location> findByName(String name);
    List<Location> findAllByLatitudeBetweenAndLongitudeBetween(BigDecimal minLat, BigDecimal maxLat, BigDecimal minLong, BigDecimal maxLong);
}
