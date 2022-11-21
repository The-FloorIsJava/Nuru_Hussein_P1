package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.DisbursementDAO;
import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.RequestStatus;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.LoginCredentials;

import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;
    private EmployeeDAO employeeDAO;
    public EmployeeService() {

    }
    public EmployeeService(EmployeeDAO employeeDAO) {

        this.employeeDAO = employeeDAO;
    }

    //Method overloaded
    public Employee createEmployee(Employee employee)  {
        return this.employeeDAO.create(employee);
    }

    //It takes user input and compare it with information in SQL-- You cant by-pass if you are not a member
    public Employee login(LoginCredentials loginCredentials){
        // TODO: IMPLEMENT ME WITH DAO
        if (this.sessionEmployee != null) return null;
        this.sessionEmployee = this.employeeDAO.loginCheck(loginCredentials.getEmployeeUsername(), loginCredentials.getEmployeePassword());
        return this.sessionEmployee;
    }
  public List<Employee> getAllEmployees() {
         return this.employeeDAO.getAll();

  }
  public Employee getEmployeeByUsername(String getObjectById) {
       // return this.employeeDAO.getByField(getObjectById);
    return  null;
  }

  public Employee getSessionEmployee() {
        return this.sessionEmployee;
  }
  public void logout(){

        this.sessionEmployee = null;
    }


    // You can approve request only and only if you are a manager
  public boolean checkIfAManagerToApprove() {
        return (this.sessionEmployee ==null) || (this.sessionEmployee.getRole() != Role.Manager);
  }

  public void submitRequest(Disbursement disbursement) {
        Employee requestor = this.getSessionEmployee();
        if (requestor == null) {
            System.out.println("Log in to submit");
        } else if (disbursement.getAmount() >= 0 && disbursement.getDescription() != null) {
            disbursement.setRequestStatus(RequestStatus.Pending);
            disbursement.setEmployeeId(disbursement.getEmployeeId());
            DisbursementDAO disbursementDAO = new DisbursementDAO();
            Disbursement newDisbursement = disbursementDAO.create(disbursement);
        } else {
            System.out.println("Every request must have amount greater than zero and description.");
        }
  }

}
