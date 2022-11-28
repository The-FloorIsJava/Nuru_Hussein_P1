package Com.Revature.ExpenseReimbursmentSoftware.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EmployeeReassignment {

    @JsonAlias(value = {"username",})
    private String employeeUsername;

    @JsonAlias(value = {"reassign",})
    private String reassignEmployee;

    public EmployeeReassignment() {
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getReassignEmployee() {
        return reassignEmployee;
    }

    public void setReassignEmployee(String reassignEmployee) {
        this.reassignEmployee = reassignEmployee;
    }
}
