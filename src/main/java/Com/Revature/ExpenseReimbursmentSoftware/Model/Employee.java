package Com.Revature.ExpenseReimbursmentSoftware.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee  {
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;
   // @JsonAlias(value = {"pass", "pasWORD"})
    private String password;

public Employee() {

    this.role = Role.Employee;
}
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.Employee;
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

    public boolean checkPassword(String password) {
     if (password == null) return false;
        return (this.password !=null) && this.password.equals(password);
    }
    public boolean checkPassword(Employee employee) {
        if (employee == null) return false;
        if(employee.getPassword() ==null) return false;
        return this.password != null && this.password.equals(employee.getPassword());
    }
    public boolean checkValidEmployee() {
    return (this.username !=null) && (this.password != null);
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
