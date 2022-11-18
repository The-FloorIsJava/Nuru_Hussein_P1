package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

   private static Employee sessionEmployee = null;
    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {

        this.employeeDAO = employeeDAO;
    }

//  public  static EmployeeService getEmployeeService() {
//        instance = instance ==null ? new EmployeeService() : instance;
//        return instance;
//  }

    //Method overloaded
    public Employee createEmployee(Employee employee) {
        return employeeDAO.create(employee);
    }
    public void login(String userName, String password){
        // TODO: IMPLEMENT ME WITH DAO
        sessionEmployee = employeeDAO.loginCheck(userName, password);
    }
  public List<Employee> getAllEmployees() {
         return this.employeeDAO.getAll();
  }
  public Employee getEmployeeByUsername(String getObjectById) {
       return this.employeeDAO.getById(getObjectById);
  }
    public void logout(){

        sessionEmployee = null;
    }

    public Employee getSessionCustomer(){

        return sessionEmployee;
    }
    List<Employee> employeeList;

    public EmployeeService() {
        employeeList = new ArrayList<>();
    }
//   public void createEmployee(String username, String role, String password) {
//      Employee newEmployee = new Employee(username, role, password);
//      employeeList.add(newEmployee);
//   }
}
