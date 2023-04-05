package com.example.temporalhistoryanddebug;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryRequest;
import io.temporal.internal.common.WorkflowExecutionHistory;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class WorkflowHistoryUtil {

    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    public static String getWorkflowExecutionHistoryAsJson(String workflowId, String runId) {
        WorkflowExecution build = WorkflowExecution.newBuilder()
                .setWorkflowId(workflowId)
                .setRunId(runId)
                .build();

        GetWorkflowExecutionHistoryRequest request =
                GetWorkflowExecutionHistoryRequest.newBuilder()
                        .setNamespace("default")
                        .setExecution(build)
                        .build();

//        GetWorkflowExecutionHistoryResponse workflowExecutionHistory = service.blockingStub().getWorkflowExecutionHistory(request);
//        String jsonHistory;
//        try {
//            jsonHistory = JsonFormat.printer().print(workflowExecutionHistory.getHistory());
//        } catch (Exception e) {
//            jsonHistory = "???????????????????????????????????????????!!!4444444444444444444444444444444444444444";
//        }

        String jsonHistory = new WorkflowExecutionHistory(
                service.blockingStub().getWorkflowExecutionHistory(request).getHistory()).toJson(true);
//        System.out.println(jsonHistory);

        return jsonHistory;
    }
}
