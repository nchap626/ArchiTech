package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserService {

    private final UserDAO userDao;
    private final Connection conn;
    private Long authenticatedUserId; // store authenticated user ID here

    @Autowired
    public UserService(Connection conn, UserDAO userDao) {
        this.conn = conn;
        this.userDao = userDao;
    }
    public User registerUser(String username, String password, String email) throws SQLException {
        // check if the username is already taken
        if (userDao.getUserByUsername(username) != null) {
            throw new RuntimeException("Username already taken");
        }

        // create a new User object and add it to the database
        User user = new User(username, password, email);
        userDao.addUser(user);
        return user;
    }

    public User loginUser(String username, String password) throws SQLException {
        // retrieve the user from the database by their username
        User user = userDao.getUserByUsername(username);

        // if the user doesn't exist or the password is incorrect, return null
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        // store authenticated user ID here
        authenticatedUserId = user.getId();

        // otherwise, return the authenticated user
        return user;
    }
    public void logoutUser(User user) {
        // simply invalidate the user's session or token, depending on the authentication mechanism being used
    }

    public User getUserByID(int user_id) throws SQLException {
        return userDao.getUserById(user_id);
    }

    public void updateUserLocation(Location location) {
        // check if authenticated user ID is set
        if (authenticatedUserId == null) {
            throw new RuntimeException("User not authenticated");
        }

        // update user location using authenticated user ID
        userDao.updateUserLocation(authenticatedUserId, location);
    }
}