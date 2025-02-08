import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistration {
    private DatabaseManager dbManager;

    public UserRegistration(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean registerUser(String username, String password, boolean isAdmin, String email) {
        String sql = "INSERT INTO users (username, password, is_admin, email) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setBoolean(3, isAdmin);
            pst.setString(4, email);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User loginUser(String username, String password) {
        String sql = "SELECT is_admin FROM users WHERE username = ? AND password = ?";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    boolean isAdmin = rs.getBoolean("is_admin");
                    return isAdmin ? new AdminUser(username, password, null) : new RegularUser(username, password, null);
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
