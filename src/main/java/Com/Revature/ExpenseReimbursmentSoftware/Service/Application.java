package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.Controller.DisbursementController;
import Com.Revature.ExpenseReimbursmentSoftware.Controller.EmployeeController;
import Com.Revature.ExpenseReimbursmentSoftware.DAO.DisbursementDAO;
import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import io.javalin.Javalin;

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
       new EmployeeController(app).startJavalinAPIEmployeeEndPoint();
       new DisbursementController(app).startJavalinAPIDisbursementEndPoint();
    }
}
