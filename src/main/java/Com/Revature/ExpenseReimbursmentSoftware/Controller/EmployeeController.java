package Com.Revature.ExpenseReimbursmentSoftware.Controller;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.LoginCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class EmployeeController {


  // private final EmployeeService employeeService;

//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = new EmployeeService(new EmployeeDAO());
//    }
  EmployeeService employeeService;
  Javalin app;
     /*
     * Javalin app initialized to create endpoint
     * */
    public EmployeeController(Javalin app) {
         employeeService = new EmployeeService(new EmployeeDAO());
        this.app = app;
    }
    public void startJavalinAPIEmployeeEndPoint() {
        //Javalin app = Javalin.create().start(8080);
        app.get("test", this::testerHandler);
        app.post("employee", this::createEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{name}", this::getEmployeeByUsername);
        app.post("login", this::loginInCheckHandler);
        app.delete("logout", this::logoutHandler);
        app.post("submitRequest", this::createDisbursementHandler);
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
     String name = context.pathParam("name");
     Employee employee = employeeService.getEmployeeByUsername(name);
     context.json(employee);

    }

    private void getAllEmployeesHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }


    private void testerHandler(Context context) {
        context.result("This test was Successful!!!");
    }
    private void createEmployeeHandler(Context context) throws JsonProcessingException, SQLIntegrityConstraintViolationException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(context.body(), Employee.class);
        employeeService.createEmployee(employee);
//      if (employee == null) {
//            context.json("Your account is not registered!");
//        }
        context.json(employee);
        //context.result("Employee already exist");
    }
    private void loginInCheckHandler(Context context)  throws  JsonProcessingException{
      ObjectMapper objectMapper = new ObjectMapper();
        LoginCredentials loginCredentials = objectMapper.readValue(context.body(), LoginCredentials.class);
        if(this.employeeService.login(loginCredentials) == null) context.json(String.format("%s login was not successful. Check your credential or log out to log in!", loginCredentials.getEmployeeUsername()));
         else context.json(String.format("%s successfully logged in!", loginCredentials.getEmployeeUsername()));
    }
    private void createDisbursementHandler(Context context) throws JsonProcessingException {
        Employee employee = this.employeeService.getSessionEmployee();
        if(employee == null)  {
            context.json("Please, log in to send your request!");
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Disbursement request = objectMapper.readValue(context.body(), Disbursement.class);
        //if(this.employeeService.submitRequest(request) == null) context.json("Failed to submit request!");
        context.json(String.format("The request number  submitted successfully!", request.getId()));
    }
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Disbursement disbursement = objectMapper.readValue(context.body(), Disbursement.class);
//        employeeService.submitRequest(disbursement);
//        context.json(disbursement);
//    }
}
