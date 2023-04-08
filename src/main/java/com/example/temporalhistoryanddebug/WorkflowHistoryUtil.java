package com.example.temporalhistoryanddebug;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.api.enums.v1.WorkflowExecutionStatus;
import io.temporal.api.workflow.v1.WorkflowExecutionInfo;
import io.temporal.api.workflowservice.v1.GetWorkflowExecutionHistoryRequest;
import io.temporal.api.workflowservice.v1.ListWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListWorkflowExecutionsResponse;
import io.temporal.client.WorkflowClient;
import io.temporal.internal.common.WorkflowExecutionHistory;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.List;

public class WorkflowHistoryUtil {

    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    private static final WorkflowClient client = WorkflowClient.newInstance(service);

    public static String getWorkflowExecutionHistoryAsJson(String workflowId) {
        ListWorkflowExecutionsResponse newCustomersResponse =
                getExecutionsResponse(
                        "WorkflowType='WorkFlow'");
        List<WorkflowExecutionInfo> newExecutionInfo = newCustomersResponse.getExecutionsList();
        WorkflowExecutionInfo executionInfo = newExecutionInfo
                .stream().filter(workflowExecutionInfo -> workflowExecutionInfo.getExecution()
                        .getWorkflowId().equals(workflowId))
                .findFirst()
                .get();

        WorkflowExecution build = WorkflowExecution.newBuilder()
                .setWorkflowId(workflowId)
                .setRunId(executionInfo.getExecution().getRunId())
                .build();

        GetWorkflowExecutionHistoryRequest request =
                GetWorkflowExecutionHistoryRequest.newBuilder()
                        .setNamespace(client.getOptions().getNamespace())
                        .setExecution(build)
                        .build();

        return new WorkflowExecutionHistory(
                service.blockingStub().getWorkflowExecutionHistory(request).getHistory()).toJson(true);
    }

    private static ListWorkflowExecutionsResponse getExecutionsResponse(String query) {
        ListWorkflowExecutionsRequest listWorkflowExecutionRequest =
                ListWorkflowExecutionsRequest.newBuilder()
                        .setNamespace(client.getOptions().getNamespace())
                        .setQuery(query)
                        .build();
        ListWorkflowExecutionsResponse listWorkflowExecutionsResponse =
                service.blockingStub().listWorkflowExecutions(listWorkflowExecutionRequest);
        return listWorkflowExecutionsResponse;
    }
}
