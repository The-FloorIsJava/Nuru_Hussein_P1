package Com.Revature.ExpenseReimbursmentSoftware.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee  {
    private String username;
    private Role role;
    //private String role;
    //@JsonAlias(value = {"pass", "pasWORD"})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

public Employee() {

    this.role = Role.Employee;
}
    public Employee(String username,Role role, String password) {
        this.username = username;
        this.role = Role.Employee;
        this.password = password;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAValidEmployee() {
    return (this.password !=null) & (this.username !=null);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
