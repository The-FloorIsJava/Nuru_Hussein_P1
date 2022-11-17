package Com.Revature.ExpenseReimbursmentSoftware.Model;

public class Employee {
    private String username;
    private String role;
    private String password;
public Employee() {

}

    public Employee(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkEmail(String password) {
     if (password == null) return false;
        return (this.password !=null) && this.password.equals(password);
    }
    public boolean checkEmployee() {
    return (this.username !=null) & (this.role != null);
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
