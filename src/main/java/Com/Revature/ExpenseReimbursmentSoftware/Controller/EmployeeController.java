package Com.Revature.ExpenseReimbursmentSoftware.Controller;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.LoginCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {


    EmployeeService employeeService;

    //Javalin app;


    public EmployeeController() {
        employeeService = new EmployeeService(new EmployeeDAO());
    }

    public void startJavalinAPI() {
        Javalin app = Javalin.create().start(8080);
        app.get("test", this::testerHandler);
        app.post("employee", this::createEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{name}", this::getEmployeeByUsername);
        app.post("login", this::loginInCheck);
    }


    private void getEmployeeByUsername(Context context) {
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
    private void createEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue(context.body(), Employee.class);
        employeeService.createEmployee(employee);
        context.json(employee);
    }
    private void loginInCheck(Context context)  throws  JsonProcessingException{
      ObjectMapper objectMapper = new ObjectMapper();
        LoginCredentials loginCredentials = objectMapper.readValue(context.body(), LoginCredentials.class);
        employeeService.login(loginCredentials.getEmployeeUsername(), loginCredentials.getEmployeePassword());
         context.json("You are successfully logged in");
    }


}
