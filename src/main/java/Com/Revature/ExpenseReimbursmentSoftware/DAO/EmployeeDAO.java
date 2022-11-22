package Com.Revature.ExpenseReimbursmentSoftware.DAO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.Exceptions.InvalidCustomerCredentialException;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import Com.Revature.ExpenseReimbursmentSoftware.Util.Interface.CrudOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAO implements CrudOperation<Employee> {


    @Override
    public Employee create(Employee newEmployee) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            String sql = "insert into users_login(employee_username, employee_role, employee_password)" + "values(?,?::user_role,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmployee.getUsername());
            preparedStatement.setString(2, newEmployee.getRole().toString());
            preparedStatement.setString(3, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException("failed to insert employee to database");
            }
            return newEmployee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Employee> getAll() {
        //Try Connecting to DB
        try( Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            List<Employee> employees = new ArrayList<>();
          // Select * employees from users_login table

            String sql = "SELECT * FROM users_login";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

        //traverse through employees
            while(resultSet.next()) {
                employees.add(convertInfoToEmployee(resultSet));
            }
            return employees;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getByField(String getEmployeeByUsername) {
        //Try Connecting to DB
        try( Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            // Select * employees from users_login table
            String sql = "SELECT * FROM users_login WHERE employee_username = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, getEmployeeByUsername);

            ResultSet rs = ps.executeQuery();

          // traverse through employees
            while(!rs.next()) throw new InvalidCustomerCredentialException("User not found!");
            Employee employee = new Employee();
            employee.setUsername(rs.getString("employee_username"));
            employee.setRole(Role.valueOf(rs.getString("employee_role")));
            employee.setPassword(rs.getString("employee_password"));
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
    public void delete(Employee employeeUsername) {
        String sql = "DELETE FROM users_login WHERE employee_username = ?";

        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employeeUsername.getUsername());

            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee loginCheck(String employeeUsername, String employeePassword) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            String sql = "select * from users_login where employee_username = ? and employee_password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeUsername);
            preparedStatement.setString(2,employeePassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            //column headers return - employee_username, employee_role, employee_password
            if (!resultSet.next()) {
                throw new InvalidCustomerCredentialException("Incorrect username or password.");
            }

            return convertInfoToEmployee(resultSet);
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
