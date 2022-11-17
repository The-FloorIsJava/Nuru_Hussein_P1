package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;

public class EmployeeService {

    private Employee sessionEmployee = null;

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    //Method overloaded
    public Employee createEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }
    public void login(String customerName, String password){
        // TODO: IMPLEMENT ME WITH DAO
        sessionEmployee = employeeDAO.loginCheck(customerName, password);
    }

    public void logout(){

        sessionEmployee = null;
    }

    public Employee getSessionCustomer(){

        return sessionEmployee;
    }

}
