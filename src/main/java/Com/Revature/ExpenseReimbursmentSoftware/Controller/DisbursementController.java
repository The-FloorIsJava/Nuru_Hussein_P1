package Com.Revature.ExpenseReimbursmentSoftware.Controller;

import Com.Revature.ExpenseReimbursmentSoftware.Model.Disbursement;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Service.DisbursementService;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import Com.Revature.ExpenseReimbursmentSoftware.Util.DTO.DisbursementRequestProcess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

public class DisbursementController {

  private final   DisbursementService disbursementService;
  private final EmployeeService employeeService;
 Javalin app;

    public DisbursementController(DisbursementService disbursementService, EmployeeService employeeService) {
        this.disbursementService = disbursementService;
        this.employeeService = employeeService;
    }

    public void startJavalinAPIDisbursementEndPoint(Javalin app) {
       // Javalin app = Javalin.create().start(8080);
        app.post("request/send", this::createDisbursementHandler);
        app.get("request/previous", this::getAllOldRequestHandler);
        app.get("request/allPending", this::getAllPendingRequestHandler);
        app.post("request/approval", this::createRequestApprovalHandler);
    }

    private void getAllOldRequestHandler(Context context) {
        Employee employee = this.employeeService.getSessionEmployee();
        if (employee == null) {
            context.json("Please log in to see your previous request.");
            return;
        }
        List<Disbursement> disbursements;
        disbursements = this.disbursementService.getAllOldRequests(employee);

        if (disbursements == null) {
            context.json("No Previous requests found");
            return;
        }
        context.json(disbursements);

    }

    private void  createRequestApprovalHandler(Context context) throws JsonProcessingException {
        if(this.employeeService.checkIfAManagerToApprove()) {
            context.json("Push hard to senior level then to Manager view this page.Only managers are privileged to view this page");
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        DisbursementRequestProcess process = objectMapper.readValue(context.body(), DisbursementRequestProcess.class);
        if (this.disbursementService.ProcessRequest(process) == null) context.json("Request process failed!");
        else context.json(String.format("%d request number processed successfully", process.getRequestId()));
    }

    private void getAllPendingRequestHandler(Context context) {
        if(this.employeeService.checkIfAManagerToApprove()) {

            context.json("Only managers are privileged to view this page");
            return;
        }
        List<Disbursement> allPendingRequests = disbursementService.getPendingRequests();
        context.json(allPendingRequests);
    }

    private void createDisbursementHandler(Context context) throws JsonProcessingException {
        Employee employee = this.employeeService.getSessionEmployee();
        if(employee == null)  {
            context.json("Please, log in to send your request!");
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Disbursement request = objectMapper.readValue(context.body(), Disbursement.class);
        if(this.disbursementService.sendRequest(request, employee) == null) context.json("Failed to submit request!");
        else context.json(String.format("request number  submitted successfully!", request.getId()));
    }

}
