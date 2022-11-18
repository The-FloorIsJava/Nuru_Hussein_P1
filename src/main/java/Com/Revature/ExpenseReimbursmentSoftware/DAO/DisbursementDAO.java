package Com.Revature.ExpenseReimbursmentSoftware.DAO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DatabaseConnectionFactory;
import Com.Revature.ExpenseReimbursmentSoftware.Util.Interface.CrudOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DisbursementDAO implements CrudOperation<Disbursement> {

    @Override
    public Disbursement create(Disbursement newDisbursement) {
        try (Connection connection = DatabaseConnectionFactory.getDatabaseConnectionFactory().getConnection()) {
        String sql = "insert into reimbursement_ticket(employeeId, amount, description, status) values(?,?, ? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, newDisbursement.getId());
        preparedStatement.setString(2, newDisbursement.getEmployeeId());
        preparedStatement.setString(3, newDisbursement.getDescription());
        preparedStatement.setString(4, newDisbursement.getStatus());
        int checkInsert = preparedStatement.executeUpdate();
        if (checkInsert == 0) {
                throw new RuntimeException("Disbursement was not inserted to database");
        }
        return newDisbursement;
    } catch (Exception e) {

    }
        return null;
    }

    @Override
    public List<Disbursement> getAll() {
        //EmployeeService employeeService = EmployeeService.getEmployeeService();
        return null;
    }

    @Override
    public Disbursement getById(String getDisrbursementById) {
        return null;
    }

    @Override
    public boolean updated(Disbursement updatedDisbursement) {
        return false;
    }

    @Override
    public boolean delete(int employeeId) {
        return false;
    }

}
