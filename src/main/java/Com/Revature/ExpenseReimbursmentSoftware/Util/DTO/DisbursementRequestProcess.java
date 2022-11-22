package Com.Revature.ExpenseReimbursmentSoftware.Util.DTO;

import Com.Revature.ExpenseReimbursmentSoftware.Model.RequestStatus;
import com.fasterxml.jackson.annotation.JsonAlias;

public class DisbursementRequestProcess {

    @JsonAlias(value = {"id",})
    private int RequestId;

    @JsonAlias(value = {"status",})
    private String process;

    public DisbursementRequestProcess() {
    }

    public int getRequestId() {
        return RequestId;
    }

    public void setRequestId(int RequestId) {
        this.RequestId = RequestId;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
