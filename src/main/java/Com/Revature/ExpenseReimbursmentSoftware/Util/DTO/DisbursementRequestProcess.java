package Com.Revature.ExpenseReimbursmentSoftware.Util.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DisbursementRequestProcess {

    @JsonAlias(value = {"id",})
    private int requestId;

    @JsonAlias(value = {"status",})
    private String process;

    public DisbursementRequestProcess() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int RequestId) {
        this.requestId = RequestId;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
