package Com.Revature.ExpenseReimbursmentSoftware.Model;

public class Disbursement {
    private int id;
    private String employeeId;
    private double amount;
    private String description;
    private String status;


    public Disbursement(int id, String employeeId, double amount, String description, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Disbursement{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
