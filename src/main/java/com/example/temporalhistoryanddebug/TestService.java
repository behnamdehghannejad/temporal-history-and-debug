package com.example.temporalhistoryanddebug;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class TestService {

    private final WorkflowClient workflowClient;
    private final WorkFlowInfoRepository repository;

    private WorkFlow createWorkFlowConnection(String workFlowId) {
        RetryOptions retryoptions = RetryOptions.newBuilder()
                .setMaximumAttempts(1)
                .build();

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowRunTimeout(Duration.ofHours(12))
                .setTaskQueue("1111111111111")
                .setRetryOptions(retryoptions)
                .setWorkflowId(workFlowId)
                .build();

        return workflowClient.newWorkflowStub(WorkFlow.class, options);
    }
    public String start() {
        WorkFlow workFlowConnection = createWorkFlowConnection("1111111111111222222222222222");
        String start = workFlowConnection.start();
        var workflowExecution = WorkflowClient.start(workFlowConnection::start);
        String extracted = extracted(start, "1111111111111222222222222222");

        WorkflowLog workflowLog = WorkflowLog.builder()
                .history(extracted)
                .build();

        repository.insert(workflowLog);
        return start;
    }

    private String extracted(String runId, String workflowId) {

        return WorkflowHistoryUtil.getWorkflowExecutionHistoryAsJson(workflowId, runId);

//        System.out.println(jsonHistory);
    }
}
