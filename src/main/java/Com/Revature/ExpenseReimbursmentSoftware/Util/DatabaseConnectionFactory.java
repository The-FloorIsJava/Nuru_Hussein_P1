package Com.Revature.ExpenseReimbursmentSoftware.Util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {

    private static final DatabaseConnectionFactory databaseConnectionFactory = new DatabaseConnectionFactory();

    private static Properties properties = new Properties();


    public DatabaseConnectionFactory() {
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Specify to the DriverManager which JDBC drivers to try to make Connections and loading drivers using forName() method.
    static {
        try {
            Class.forName("org.postgres.driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionFactory getDatabaseConnectionFactory() {
        return databaseConnectionFactory;
    }

    //To get connection object and registering drivers using DriverManager
    public static Connection getConnection() {
            try {
                Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }
}
