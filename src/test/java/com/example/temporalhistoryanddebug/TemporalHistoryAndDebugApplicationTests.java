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
        WorkflowLog workflowLog = workFlowInfoRepository.findById("63d64b0c37ad746ea8b4af67").get();
        String jsonHistory = workflowLog.getHistory();

        System.out.println(jsonHistory);
        WorkflowReplayer.replayWorkflowExecution(
                jsonHistory, WorkflowImpl.class);
    }
}
