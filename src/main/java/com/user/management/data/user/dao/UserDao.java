package com.user.management.data.user.dao;

import com.user.management.appl.model.user.User;

import java.util.List;
/**
 * This is the User Dao.
 * */
public interface UserDao {
    User findUserByUsername(String username);
    User saveUser(User user);
    long getMaxUserId();
    /**
     * Retrieves a list of all users from the database.
     */
    List<User> getAllUsers() ;
    User getUserById(int id);
    boolean updateUser();
    User getUsername(String username);
    User updatePassword(User user);

    String getPasswordByUsername(String username);
    String forgotPassword(String username, String newPassword);

}
