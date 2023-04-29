package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

//@SuppressWarnings("ALL")
public class VisitedLocationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Connection conn;

    public VisitedLocationDAO(Connection conn) {
        this.conn = conn;
    }

    public void addVisitedLocation(int userId, int locationId, LocalDateTime dateVisited) {
        String sql = "INSERT INTO Visited_Locations (user_id, location_id, date_visited) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userId, locationId, dateVisited);
    }

    public List<VisitedLocation> getVisitedLocationsByUserId(int userId) {
        String sql = "SELECT * FROM Visited_Locations WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new VisitedLocationRowMapper());
    }

    public List<VisitedLocation> getVisitedLocationsByLocationId(int locationId) {
        String sql = "SELECT * FROM Visited_Locations WHERE location_id = ?";
        return jdbcTemplate.query(sql, new Object[]{locationId}, new VisitedLocationRowMapper());
    }

    public List<VisitedLocation> getVisitedLocationsByDateVisited(LocalDateTime dateVisited) {
        String sql = "SELECT * FROM Visited_Locations WHERE date_visited = ?";
        return jdbcTemplate.query(sql, new Object[]{dateVisited}, new VisitedLocationRowMapper());
    }

    public void deleteVisitedLocationById(int visitedLocationId) {
        String sql = "DELETE FROM Visited_Locations WHERE id = ?";
        jdbcTemplate.update(sql, visitedLocationId);
    }

    public void deleteVisitedLocationsByUserId(int userId) {
        String sql = "DELETE FROM Visited_Locations WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public void deleteVisitedLocationsByLocationId(int locationId) {
        String sql = "DELETE FROM Visited_Locations WHERE location_id = ?";
        jdbcTemplate.update(sql, locationId);
    }

    public void deleteVisitedLocationsByDateVisited(LocalDateTime dateVisited) {
        String sql = "DELETE FROM Visited_Locations WHERE date_visited = ?";
        jdbcTemplate.update(sql, dateVisited);
    }

    private static final class VisitedLocationRowMapper implements RowMapper<VisitedLocation> {
        public VisitedLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
            VisitedLocation visitedLocation = new VisitedLocation();
            visitedLocation.setId(rs.getLong("id"));
            visitedLocation.setUserId(rs.getInt("user_id"));
            visitedLocation.setLocationId(rs.getInt("location_id"));
            visitedLocation.setDateVisited(rs.getTimestamp("date_visited").toLocalDateTime());
            return visitedLocation;
        }
    }
}