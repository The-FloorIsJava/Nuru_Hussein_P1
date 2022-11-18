package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.Controller.EmployeeController;

public class Application {

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.startJavalinAPI();
    }
}
