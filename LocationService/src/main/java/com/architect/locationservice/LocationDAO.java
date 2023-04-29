package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.util.List;

public class LocationDAO {

    private final Connection conn;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LocationDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert a new location into the database
    public void addLocation(Location location) {
        String sql = "INSERT INTO Locations (name, description, picture, latitude, longitude) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, location.getName(), location.getDescription(), location.getPicture(), location.getLatitude(), location.getLongitude());
    }

    // Retrieve all locations from the database
    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM Locations";
        RowMapper<Location> rowMapper = new LocationRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Retrieve a specific location by its ID from the database
    public Location getLocationById(int id) {
        String sql = "SELECT * FROM Locations WHERE id = ?";
        RowMapper<Location> rowMapper = new LocationRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Update a location's details in the database
    public void updateLocation(Location location) {
        String sql = "UPDATE Locations SET name = ?, description = ?, picture = ?, latitude = ?, longitude = ? WHERE id = ?";
        jdbcTemplate.update(sql, location.getName(), location.getDescription(), location.getPicture(), location.getLatitude(), location.getLongitude(), location.getId());
    }

    // Delete a location from the database
    public void deleteLocation(int id) {
        String sql = "DELETE FROM Locations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Map each row of the Locations table to a Location object
    private static class LocationRowMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
            Location location = new Location();
            location.setId((long) resultSet.getInt("id"));
            location.setName(resultSet.getString("name"));
            location.setDescription(resultSet.getString("description"));
            location.setPicture(resultSet.getString("picture"));
            location.setLatitude(resultSet.getDouble("latitude"));
            location.setLongitude(resultSet.getDouble("longitude"));
            return location;
        }
    }
}
