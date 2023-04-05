package com.example.temporalhistoryanddebug;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import io.temporal.workflow.WorkflowInfo;

import java.time.Duration;


public class WorkflowImpl implements WorkFlow{

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(10))
            .setBackoffCoefficient(1)
            .setMaximumAttempts(2)
            .build();

    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(2))
            .setRetryOptions(retryoptions)
            .build();

    private final Activity activity = Workflow.newActivityStub(Activity.class, activityOptions);

    @Override
    public String start() {
        System.out.println("============== startWorkflow =====> ");
        String start;
        try {
            start = activity.start();
        } catch (RuntimeException e) {
            return "Exception Catch";
        }
        WorkflowInfo info = Workflow.getInfo();
        String runId = info.getRunId();

        WorkflowLog workflowLog = WorkflowLog.builder()
                .workflowInfo(info.toString())
                .build();

//        return start;

        return runId;

    }
}
