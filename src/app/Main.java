import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/my_database";
        String user = "postgres";
        String password = "myPassword";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('Name1', 1, 'Name1@gmail.com')");
            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('Name2', 2, 'Name2@gmail.com')");
            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('Name3', 3, 'Name3@gmail.com')");

            System.out.println("After insert:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " +
                        rs.getInt("age") + " " + rs.getString("email"));
            }

            stmt.executeUpdate("DELETE FROM users WHERE name = 'Name3'");

            System.out.println("\nAfter delete:");
            rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " +
                        rs.getInt("age") + " " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
