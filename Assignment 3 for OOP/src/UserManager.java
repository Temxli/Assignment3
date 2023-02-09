import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class UserManager implements UserManagerInterface {

    public static void insertUserData(String username, String password){
        String sql = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";

        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static boolean checkUserData(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        boolean isUserPresent = false;
        try (Connection conn = Main.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            isUserPresent = resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isUserPresent;
    }
    static void getAllUsers() {
        String sql = "SELECT username, password FROM users";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            System.out.println("\nUsername\tPassword");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username") + "\t\t" + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = Main.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static void updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = Main.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteUserData(String username) {

    }

    @Override
    public void changePassword(String username, String newPassword) {

    }
}
