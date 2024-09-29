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
import model.GameMatch;

/**
 *
 * @author admin
 */
public class GameMatchDAO extends DBContext {

    // Create a new match
    public void createGameMatch(int userId1, int userId2, int state) {
        String sql = "INSERT INTO GameMatch (userid1, userid2, state) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            ps.setInt(3, state);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read match by id
    public GameMatch getGameMatchById(int id) {
        String sql = "SELECT * FROM GameMatch WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new GameMatch(
                        rs.getInt("id"),
                        rs.getInt("userid1"),
                        rs.getInt("userid2"),
                        rs.getInt("state")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all matches
    public List<GameMatch> getAllGameMatches() {
        List<GameMatch> gameMatches = new ArrayList<>();
        String sql = "SELECT * FROM GameMatch";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                gameMatches.add(new GameMatch(
                        rs.getInt("id"),
                        rs.getInt("userid1"),
                        rs.getInt("userid2"),
                        rs.getInt("state")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameMatches;
    }

    // Update match state
    public void updateGameMatchState(int id, int state) {
        String sql = "UPDATE GameMatch SET state = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, state);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete match by id
    public void deleteGameMatch(int id) {
        String sql = "DELETE FROM GameMatch WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
