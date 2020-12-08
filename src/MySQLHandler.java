import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLHandler {
    private String password;
    private Connection connection;

    public MySQLHandler() {
        JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        password = new String(pf.getPassword());

        try {
            // Set up connection to database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/entry_manager? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "elloot", password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Map<String, String>> readUsers() {
        try {
            // Setup statement
            Statement stmt = connection.createStatement();

            // Create query and execute
            String SQLQuery = "select * from users";
            ResultSet rset = stmt.executeQuery(SQLQuery);

            // Loop through the result set and print
            // Need to know the table-structure
            ArrayList<Map<String, String>> users = new ArrayList<>();
            while (rset.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("id", rset.getString("id"));
                user.put("name", rset.getString("name"));
                user.put("email", rset.getString("email"));
                user.put("password", rset.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Map<String, String>> readEntries() {
        try {
            // Setup statement
            Statement stmt = connection.createStatement();

            // Create query and execute
            String SQLQuery = "select * from entries";
            ResultSet rset = stmt.executeQuery(SQLQuery);

            // Loop through the result set and print
            // Need to know the table-structure
            ArrayList<Map<String, String>> entries = new ArrayList<>();
            while (rset.next()) {
                Map<String, String> entry = new HashMap<>();
                entry.put("id", rset.getString("id"));
                entry.put("content", rset.getString("content"));
                entry.put("createdAt", rset.getString("created_at"));
                entry.put("updatedAt", rset.getString("updated_at"));
                entry.put("authorId", rset.getString("author_id"));
                entry.put("title", rset.getString("title"));
                entries.add(entry);
            }
            return entries;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
