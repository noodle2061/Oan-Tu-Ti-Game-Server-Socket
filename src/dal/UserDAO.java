/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author admin
 */
public class UserDAO extends DBContext{
// Create new user

    public void createUser(String name, String password, String avatar) {
        String sql = "INSERT INTO User (name, password, avatar) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, avatar);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read user by id
    public User getUserById(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("match_count"),
                        rs.getInt("win_count"),
                        rs.getInt("draw_count"),
                        rs.getInt("lose_count"),
                        rs.getInt("points"),
                        rs.getString("avatar")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getInt("match_count"),
                        rs.getInt("win_count"),
                        rs.getInt("draw_count"),
                        rs.getInt("lose_count"),
                        rs.getInt("points"),
                        rs.getString("avatar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update user
    public void updateUser(User user) {
        String sql = "UPDATE User SET name = ?, password = ?, match_count = ?, win_count = ?, draw_count = ?, lose_count = ?, points = ?, avatar = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getMatchCount());
            ps.setInt(4, user.getWinCount());
            ps.setInt(5, user.getDrawCount());
            ps.setInt(6, user.getLoseCount());
            ps.setInt(7, user.getPoints());
            ps.setString(8, user.getAvatar());
            ps.setInt(9, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete user by id
    public void deleteUser(int id) {
        String sql = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
