package Com.Revature.ExpenseReimbursmentSoftware.Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.DisbursementDAO;

public class DisbursementService {
 private static DisbursementService instance;

 private DisbursementDAO disbursementDAO;

 private DisbursementService(DisbursementDAO disbursementDAO) {
     this.disbursementDAO = disbursementDAO;
 }

}
