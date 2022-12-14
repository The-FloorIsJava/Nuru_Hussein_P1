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
import java.util.Locale;

public class DisbursementDAO implements CrudOperation<Disbursement> {

    @Override
    public Disbursement create(Disbursement newDisbursement) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
        String sql = "insert into reimbursement_ticket(employee_id, amount, description) values(?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newDisbursement.getEmployeeId());
        preparedStatement.setDouble(2,newDisbursement.getAmount());
        preparedStatement.setString(3, newDisbursement.getDescription());
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

            String sql = "SELECT * FROM reimbursement_ticket WHERE employee_id = ? " +
                    "order By reimbursement_ticket.id";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employeeRequests.add(convertSQLResultToDisbursementRequest(resultSet));
            }

            return employeeRequests;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Disbursement getByField(String getDisbursementById) {
        List<Disbursement> disbursementList = new ArrayList<>();

        return null;
    }

    @Override
    public boolean updated(Disbursement updatedDisbursement) {
        return false;
    }

    @Override
    public Disbursement delete(String employeeId) {
        return null;
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

            //get List of pending requests (You don't have a privilege to see this part if your role ! a MANAGER)
            List<Disbursement> pendingRequests = new LinkedList<>();

            String sql = "SELECT * FROM reimbursement_ticket " +
                    "WHERE reimbursement_ticket.status = 'Pending'" +
                    "ORDER BY reimbursement_ticket.id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                pendingRequests.add(convertSQLResultToDisbursementRequest(resultSet));
            }
            return pendingRequests;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
   public DisbursementRequestProcess processRequest(DisbursementRequestProcess process) {
        try(Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {

            String sql = "UPDATE reimbursement_ticket set status =?::request_status WHERE id = ? and status = 'Pending'::request_status";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, process.getProcess());
            preparedStatement.setInt(2, process.getRequestId());

            if (preparedStatement.executeUpdate() == 0) throw new SQLException("Request process failed!");

            return process;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
   }

    private Disbursement convertSQLResultToDisbursementRequest(ResultSet resultSet) throws SQLException {
        Disbursement request = new Disbursement();

        request.setId(resultSet.getInt("id"));
        request.setEmployeeId(resultSet.getString("employee_id"));
        request.setAmount(resultSet.getDouble("amount"));
        request.setDescription(resultSet.getString("description"));
        request.setRequestStatus(resultSet.getString("status"));

        return request;
    }

}
