package Com.Revature.ExpenseReimbursmentSoftware.database;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CreateEmployeeDatabase {
/*   private static String url="jdbc:postgresql://localhost:5433/postgres";
   private static   String username="postgres";
    private static String password= "password";
*/
   private static Properties properties = new Properties();
    public CreateEmployeeDatabase() {
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
    //Method to create User Login database that takes natural key
    public static void CreateUserLoginTable()  {

        //Creating Employee table with natural key
        String sql = "CREATE TABLE USERS_LOGIN " +
                "(employee_username VARCHAR(255) not NULL, " +
                " employee_role bool Not null default false, " +
                " employee_password VARCHAR(255)," +
                " PRIMARY KEY ( employee_username ))";
        System.out.println("Table created successfully in registered database");

      try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
           Statement statement = connection.createStatement()) {
               //Create new table
          statement.execute(sql);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
       }
    }
    public static void Create() {

    }
    public static void main(String[] args) {
       // CreateUserLoginTable();
    }
}
