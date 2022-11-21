package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.Controller.DisbursementController;
import Com.Revature.ExpenseReimbursmentSoftware.Controller.EmployeeController;
import Com.Revature.ExpenseReimbursmentSoftware.DAO.DisbursementDAO;
import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import io.javalin.Javalin;

public class Application {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DisbursementDAO disbursementDAO = new DisbursementDAO();
        EmployeeService employeeService = new EmployeeService(employeeDAO);
        DisbursementService disbursementService = new DisbursementService(disbursementDAO);

        EmployeeController employeeController = new EmployeeController(employeeService);
        DisbursementController disbursementController  = new DisbursementController(disbursementService, employeeService);
        Javalin app = Javalin.create().start(8080);
       employeeController.startJavalinAPIEmployeeEndPoint(app);
       disbursementController.startJavalinAPIDisbursementEndPoint(app);
    }
}
