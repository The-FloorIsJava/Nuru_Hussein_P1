package Com.Revature.ExpenseReimbursmentSoftware.DAO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import Com.Revature.ExpenseReimbursmentSoftware.Util.Interface.CrudOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmployeeDAO implements CrudOperation<Employee> {
    @Override
    public Employee create(Employee newEmployee) {
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
//            String sql = "insert into users_login() values(" +
//                    "\'" + newEmployee.getEmployeeUserName() + "\', \'"
//                    + newEmployee.getEmployeeRole() + "\', \'"
//                    + newEmployee.getEmployeePassword();
            String sql = "insert into users_login(employeeUsername, employeeRole, employeePassword) values(?, ? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmployee.getUsername());
            preparedStatement.setString(2, newEmployee.getRole());
            preparedStatement.setString(3, newEmployee.getPassword());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {

            }
            return newEmployee;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee getById() {
        return null;
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
        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
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
}
