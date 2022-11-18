package Com.Revature.ExpenseReimbursmentSoftware.Util.DTO;

public class LoginCredentials  {

    private String employeeUsername;
    private String employeePassword;


//    public LoginCredentials(String employeeUsername, String employeePassword) {
//        EmployeeUsername = employeeUsername;
//        EmployeePassword = employeePassword;
//    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        employeePassword = employeePassword;
    }
}
