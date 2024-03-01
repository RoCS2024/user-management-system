/**
 * This serves as a class that implements the UserDao
 *
 */

package com.user.management.data.user.dao.impl;

import com.user.management.app.model.user.User;
import com.user.management.data.connection.ConnectionHelper;
import com.user.management.data.user.dao.UserDao;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM login WHERE id = ?";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idNum = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String entity_id = rs.getString("entity_id");
                    Timestamp date_modified = rs.getTimestamp("date_modified");
                    return new User(idNum, username, password, entity_id, null, date_modified);
                } else {
                    System.err.println("No user found with ID: " + id);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving user with ID " + id + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE login SET username = ?, password = ?, entity_id = ?, date_modified = ? WHERE id = ?";
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEntity_id());
            stmt.setTimestamp(4, user.getDate_modified());
            stmt.setInt(5, user.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("Error updating user with ID " + user.getId() + ": " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}