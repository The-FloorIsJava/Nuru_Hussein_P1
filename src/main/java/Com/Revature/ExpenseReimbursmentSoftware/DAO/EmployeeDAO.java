package Com.Revature.ExpenseReimbursmentSoftware.DAO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import Com.Revature.ExpenseReimbursmentSoftware.Util.Interface.CrudOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements CrudOperation<Employee> {

   /* private static EmployeeDAO instance;

    private EmployeeDAO() {

    }

    public static EmployeeDAO getEmployeeDAO() {
        instance = instance == null ? new EmployeeDAO() : instance;
        return instance;
    } */
    @Override
    public Employee create(Employee newEmployee) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            String sql = "insert into users_login(employeeUsername, employeeRole, employeePassword) values(?, ?::user_role,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmployee.getUsername());
            preparedStatement.setString(2, newEmployee.getRole().toString());
            preparedStatement.setString(3, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {

            }
            return newEmployee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        //Try Connecting to DB
        try( Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            List<Employee> employees = new ArrayList<>();
          // Select * employees from users_login table
            String sql = "SELECT * FROM users_login";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        //traverse through employees
            while(rs.next()) {
                employees.add(convertInfoToEmployee(rs));
            }
            return employees;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getById(String getEmployeeByUsername) {
        //Try Connecting to DB
        try( Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            Employee employee = new Employee();
            // Select * employees from users_login table
            String sql = "SELECT * FROM users_login WHERE employee_username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, getEmployeeByUsername);
            ResultSet rs = ps.executeQuery(sql);

            //traverse through employees
            while(rs.next()) {
                employee = convertInfoToEmployee(rs);
            }
            return employee;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updated(Employee updatedEmployee) {
        return false;
    }

    @Override
    public boolean delete(int employeeId) {
        return false;
    }

    public Employee loginCheck(String employeeUsername, String employeePassword) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            String sql = "select * from users_login where employee_username = ? and employee_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeUsername);
            preparedStatement.setString(2,employeePassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException("Information Entered for " + employeeUsername + " was incorrect. PLease try again");
            }
            Employee employee = new Employee();
            employee.setUsername(resultSet.getString("employee_username"));
            employee.setPassword(resultSet.getString("employee_password"));
            return employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private Employee convertInfoToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setUsername(resultSet.getString("employee_username"));
        employee.setRole(Role.valueOf(resultSet.getString("employee_role")));
        employee.setPassword(resultSet.getString("employee_password"));
        return employee;
    }
}
