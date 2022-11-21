package Com.Revature.ExpenseReimbursmentSoftware.Model;

import java.util.Objects;

public class Disbursement {
    private int id;  // Serial primary key;
    private String employeeId;
    private double amount;
    private String description;
    private RequestStatus requestStatus;

  public Disbursement() {

  }
    public Disbursement(int id, String employeeId,  double amount, String description, RequestStatus requestStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
        this.requestStatus = RequestStatus.Pending;


        this.amount = amount;
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

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater zero");
        }
      this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disbursement that = (Disbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && Objects.equals(employeeId, that.employeeId) && Objects.equals(description, that.description) && requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, amount, description, requestStatus);
    }

    public void setEmployee(Employee employee) {
    }
}
