import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/UsersLP";
    private static final String USER = "postgres";
    private static final String PASS = "naxuihaise";
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void main(String[] args) {
        Main system = new Main();
        createTable();

        while (true) {
            system.run();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Confidential Information System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. See all registered users");
        System.out.println("4. Delete user");
        System.out.println("5. Change password");
        System.out.println("6. Quit");
        System.out.print("Enter your choice: ");


        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter username: ");
                String username = sc.next();
                System.out.println("Enter password: ");
                String password = sc.next();
                UserManager.insertUserData(username, password);
                break;
            case 2:
                System.out.println("Enter username: ");
                username = sc.next();
                System.out.println("Enter password: ");
                password = sc.next();
                if (UserManager.checkUserData(username, password)) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed, invalid username or password");
                }
                break;
            case 3:
                UserManager.getAllUsers();
                break;
            case 4:
                System.out.println("Enter the username of the user to be deleted: ");
                username = sc.next();
                UserManager.deleteUser(username);
                break;
            case 5:
                System.out.println("Enter the username of the user whose password you want to change: ");
                username = sc.next();
                System.out.println("Enter the new password: ");
                String newPassword = sc.next();
                UserManager.updatePassword(username, newPassword);
                break;
            case 6:
                System.out.println("Thanks for using!!! Goodbye!!!");
                System.exit(0);


            default:
                System.out.println("Invalid choice. Exiting...");
                break;
        }

    }

    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "id serial PRIMARY KEY,\n"
                + "username VARCHAR(250) NOT NULL,\n"
                + "password VARCHAR(250) NOT NULL\n"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}