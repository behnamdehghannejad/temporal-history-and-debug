package com.example.temporalhistoryanddebug;

import io.temporal.testing.WorkflowReplayer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class TemporalHistoryAndDebugApplicationTests {

    @Autowired
    WorkFlowInfoRepository workFlowInfoRepository;
    @Test
    public void replayFromHistory() throws Exception {
        String workflowId = "1111111111111222222222222222";
        String runId = "bc2a85d1-2efe-4e6d-b446-4b1347e2f0c0";

        WorkflowLog workflowLog = workFlowInfoRepository.findById("63d64b0c37ad746ea8b4af67").get();
        String jsonHistory = workflowLog.getHistory();

//        String jsonHistory = WorkflowHistoryUtil.getWorkflowExecutionHistoryAsJson(workflowId, runId);

        System.out.println(jsonHistory);
        WorkflowReplayer.replayWorkflowExecution(
                jsonHistory, WorkflowImpl.class);
    }
}
