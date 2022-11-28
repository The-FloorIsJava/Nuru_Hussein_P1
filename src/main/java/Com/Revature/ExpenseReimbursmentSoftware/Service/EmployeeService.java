package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.EmployeeReassignment;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.LoginCredentials;

import java.util.List;

public class EmployeeService {

    private Employee sessionEmployee = null;
    private final EmployeeDAO employeeDAO;

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
  public Employee getEmployeeByUsername( String getObjectById) {
        if (sessionEmployee == null && checkIfAManager_toProcessTicket()) {
            throw  new IllegalArgumentException("Please log in and you have to be a manager to access employee by username");
        }
        return this.employeeDAO.getByField(getObjectById);
  }
public Employee deleteEmployeeByUsername(String deleteEmployeeById) {
        if (sessionEmployee ==null && checkIfAManagerIsOnlineToAddNewEmployee()) {
            throw new IllegalArgumentException("you have to be online and manager as well to delete employee");
        }
     return this.employeeDAO.delete(deleteEmployeeById);
}
    public EmployeeReassignment ProcessEmployeeReassignment(EmployeeReassignment reassignment) {
        return this.employeeDAO.reAssignEmployee(reassignment);
    }
  public Employee getSessionEmployee() {
        return this.sessionEmployee;
  }
  public void logout(){
        this.sessionEmployee = null;
    }


    // You can approve request only and only if you are a manager
  public boolean checkIfAManager_toProcessTicket() {
        return (this.sessionEmployee ==null) || (this.sessionEmployee.getRole() != Role.Manager);

  }
public boolean checkIfAManagerIsOnlineToAddNewEmployee() {
    return (this.sessionEmployee == null) || (this.sessionEmployee.getRole() == Role.Manager);

}

}
