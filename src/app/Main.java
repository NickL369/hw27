import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/my_database";
        String user = "postgres";
        String password = "myPassword";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('John', 30, 'john@example.com')");
            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('Alice', 25, 'alice@example.com')");
            stmt.executeUpdate("INSERT INTO users (name, age, email) VALUES ('Bob', 35, 'bob@example.com')");
//перепушив з корректними данними згідно дз, але скрін не додавав новий


            System.out.println("After insert:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " +
                        rs.getInt("age") + " " + rs.getString("email"));
            }

            stmt.executeUpdate("DELETE FROM users WHERE name = 'Bob'");

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
