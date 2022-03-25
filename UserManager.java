import java.sql.*;
import java.util.*;

public class UserManager {
    private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		try {
			String dbURL = "jdbc:sqlite:books.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return dbConnection;
	}

    public boolean checkUserExists(String username, String password) {
        System.out.println("[UserManager] Checking credentials for " + username + "...");

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "';");
        ) {
            while (result.next()) { 
				return true;
			}
        }
        catch (Exception e) {
            System.out.println("[UserManager] Failed to check user: " + e.getMessage());
        }

        return false;
    }

    public void debug_printAllUsers() throws SQLException {
		System.out.println("[UserManager] Retrieving all users...");

        try (
            Connection dbConnection = getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users;");
        ) {
            while (result.next()) { // loop over bufferreader
				System.out.println(result.getString("username") + ": " + result.getString("password"));
			}

            System.out.println("Done");
        }
        catch (Exception e) {
            System.out.println("[UserManager] Failed to retrieve users: " + e.getMessage());
        }
	}
}