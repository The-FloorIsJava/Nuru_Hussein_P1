package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.DisbursementDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.DisbursementRequestProcess;

import java.util.List;

public class DisbursementService {
//  List<Disbursement> disbursementList;
//    DisbursementDAO disbursementDAO;
// private int countRequest = 0;
//
//public  DisbursementService() {
//    disbursementList = new ArrayList<>();
//}
    private final DisbursementDAO disbursementDAO;
    private int countRequest = 0;
 public DisbursementService(DisbursementDAO disbursementDAO) {
     this.disbursementDAO = disbursementDAO;
     this.startRequestCount();
 }

    public Disbursement sendRequest(Disbursement request, Employee employee) {
        request.setEmployee(employee);
        request.setId(this.countRequest++);
        return this.disbursementDAO.create(request);
    }
    private void startRequestCount() {
        this.countRequest = this.disbursementDAO.getMaxRequest();
        int currentNumOfRequest =  this.countRequest++;
        System.out.println(currentNumOfRequest);
    }

    public List<Disbursement> getPendingRequests() {
     return this.disbursementDAO.getPendingRequests();
    }
    public List<Disbursement> getAllOldRequests(Employee employee) {
        return this.disbursementDAO.getAllOldRequests(employee);
    }
    public DisbursementRequestProcess ProcessRequest(DisbursementRequestProcess process) {
     return this.disbursementDAO.processRequest(process);
    }

    public List<Disbursement> getAll() {
        return null;
    }
}
