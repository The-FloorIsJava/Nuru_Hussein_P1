package Com.Revature.ExpenseReimbursmentSoftware.Util.DTO;

public class LoginCredentials  {

    private String employeeUsername;
    private String employeePassword;

    public LoginCredentials() {

    }
    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
}
