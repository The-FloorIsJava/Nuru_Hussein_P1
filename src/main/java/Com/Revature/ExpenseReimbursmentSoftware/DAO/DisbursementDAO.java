package Com.Revature.ExpenseReimbursmentSoftware.DAO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.RequestStatus;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.DisbursementRequestProcess;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import Com.Revature.ExpenseReimbursmentSoftware.Util.Interface.CrudOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class DisbursementDAO implements CrudOperation<Disbursement, String> {

    @Override
    public Disbursement create(Disbursement newDisbursement) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
        String sql = "insert into reimbursement_ticket(employeeId, amount, description,status) values(?,?,?,?::request_status)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(2, newDisbursement.getEmployeeId());
        preparedStatement.setDouble(3,newDisbursement.getAmount());
        preparedStatement.setString(4, newDisbursement.getDescription());
        preparedStatement.setString(5, newDisbursement.getRequestStatus().toString());
        int checkNewDisbursement = preparedStatement.executeUpdate();

        if (checkNewDisbursement == 0) {
                throw new SQLException("Disbursement was not inserted to database");
        }
        return newDisbursement;

    } catch (Exception e) {
       e.printStackTrace();
            return null;
    }

    }

    @Override
    public List<Disbursement> getAll() {
        return null;
    }

    public List<Disbursement> getAllOldRequests(Employee employee) {
        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            List<Disbursement> employeeRequests = new LinkedList<>();

            String sql = "SELECT * FROM reimbursement_ticket WHERE employee_username = ? order By" +
                    "reimbursement_ticket.id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeRequests.add(convertSQLResultToDisbursementRequest(resultSet, employee));
            }

            return employeeRequests;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Disbursement getByField(String getDisbursementById, String value) {
        List<Disbursement> disbursementList = new ArrayList<>();

        return null;
    }

    @Override
    public boolean updated(Disbursement updatedDisbursement) {
        return false;
    }

    @Override
    public void delete(Disbursement employeeId) {

        //return false;
    }


    public int getMaxRequest() {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
            String sql = "SELECT max(id) from reimbursement_ticket";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                throw new SQLException("No request found!");
            }

            return rs.getInt("max");

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
    public List<Disbursement> getPendingRequests() {
        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            // List of pending requests
            List<Disbursement> pendingRequests = new LinkedList<>();

            String sql = "SELECT * FROM reimbursement_ticket join users_login" +
                    "on users_login.employee_username = reimbursement_ticket.employee_id" +
                    "WHERE reimbursement_ticket.status = 'Pending'" +
                    "ORDER BY reimbursement_ticket.id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(!resultSet.next()) {
                pendingRequests.add(convertSQLResultToDisbursementRequest(resultSet, null));
            }
            return pendingRequests;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
   public DisbursementRequestProcess updateRequest( DisbursementRequestProcess process) {
        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            String sql = "UPDATE reimbursement_ticket set status =?::status WHERE id = ? and status = 'Pending'::status";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, process.getProcess().toString());
            preparedStatement.setInt(2, process.getRequestId());

            if (preparedStatement.executeUpdate() == 0) throw new SQLException("Request update failed!");

            return process;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
   }
    private Disbursement convertSQLResultToDisbursementRequest(ResultSet resultSet, Employee employee) throws SQLException {

        Disbursement request = new Disbursement();
        if (employee == null) {
            employee = new Employee();
                    employee.setUsername(resultSet.getString("employee_username"));
                    employee.setRole(Role.valueOf(resultSet.getString("employee_role")));
                    employee.setPassword(resultSet.getString("employee_password"));
        }

        request.setId(resultSet.getInt("id"));
        request.setEmployeeId(resultSet.getString("employee_id"));
        request.setAmount(resultSet.getDouble("amount"));
        request.setDescription(resultSet.getString("description"));
        request.setRequestStatus(RequestStatus.valueOf(resultSet.getString("status")));

        return request;
    }

}
