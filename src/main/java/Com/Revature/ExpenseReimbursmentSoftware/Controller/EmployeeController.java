package Com.Revature.ExpenseReimbursmentSoftware.Controller;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.EmployeeReassignment;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.LoginCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {

 private final  EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /*
     * Javalin app initialized to create endpoint
     * */
    public void startJavalinAPIEmployeeEndPoint(Javalin app) {
        //Javalin app = Javalin.create().start(8080);
        app.get("test", this::testerHandler);
        app.post("employee", this::createEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{name}", this::getEmployeeByUsername);
        app.delete("employee/delete/{name}", this::deleteEmployeeByUsername);
        app.post("login", this::loginInCheckHandler);
        app.delete("logout", this::logoutHandler);
        app.post("employee/reassignment", this::postEmployeeReassignmentHandler);
    }

    private void postEmployeeReassignmentHandler(Context context) throws JsonProcessingException {
        if(employeeService.checkIfAManager_toProcessTicket()) {
            context.json("Only managers are privileged to reassign employee to any other role");
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeReassignment processReassignment = objectMapper.readValue(context.body(), EmployeeReassignment.class);
        if (this.employeeService.ProcessEmployeeReassignment(processReassignment) == null) context.json("Reassigning employee to new role process failed!");
        else context.json(String.format(" Employee with %s  username reassigned to new role successfully", processReassignment.getEmployeeUsername()));
    }

    private void deleteEmployeeByUsername(Context context) throws JsonProcessingException {
        if (employeeService.checkIfAManager_toProcessTicket()) {
            context.json("Only managers are privileged to delete employee by username");
            return;
        }
        String name = context.pathParam("name");
        Employee employee = new Employee();
        employee = employeeService.deleteEmployeeByUsername(name);
        //context.json(employee);

    }

    private void logoutHandler(Context context) {
            Employee currentEmployee = this.employeeService.getSessionEmployee();
            if(currentEmployee == null) {
                context.json("You must Logged in to log out!");
                return;
            }
            String employeeUsername = currentEmployee.getUsername();
            this.employeeService.logout();
            context.json(String.format("%s logged out successfully.", employeeUsername));
    }


    private void getEmployeeByUsername(Context context) throws JsonProcessingException {
        if (employeeService.checkIfAManager_toProcessTicket()) {
            context.json("Only managers are privileged to get employee by username");
            return;
        }
     String name = context.pathParam("name");
     Employee employee;
        employee = employeeService.getEmployeeByUsername(name);
        context.json(employee);
    }

    private void getAllEmployeesHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }


    private void testerHandler(Context context) {
        context.result("This test was Successful!!!");
    }
    private void createEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(context.body(), Employee.class);
     /*   if (employee.getUsername() == null) {
            context.json("Your account is not registered!, You violated not-null constraint");
          System.out.println("Your account is not registered!, You violated not-null constraint");
          return;
        } else */
        if (employeeService.checkIfAManager_toProcessTicket()) {
              context.json("Only managers allowed to register new employee");
          System.out.println("Only managers allowed to register new employee");
          return;
      }
        if (!employee.isAValidEmployee()){
            context.json("Your account is not registered!, You violated not-null constraint");
            System.out.println("Your account is not registered!, You violated not-null constraint");
            return;
        }
        if (this.employeeService.createEmployee(employee)== null) context.json(String.format("%s employee username already exists!", employee.getUsername()));
       else context.json(String.format("employee with %s  username registered successfully", employee.getUsername()));
    }

    private void loginInCheckHandler(Context context)  throws  JsonProcessingException{
      ObjectMapper objectMapper = new ObjectMapper();
        LoginCredentials loginCredentials = objectMapper.readValue(context.body(), LoginCredentials.class);
        if(this.employeeService.login(loginCredentials) == null) context.json(String.format("%s login was not successful. Check your credential or log out to log in!", loginCredentials.getEmployeeUsername()));
         else context.json(String.format("%s successfully logged in!", loginCredentials.getEmployeeUsername()));
    }
}
