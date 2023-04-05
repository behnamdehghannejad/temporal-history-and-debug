package com.example.temporalhistoryanddebug;


import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkflowImplementationOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class MainWorker {

    @Autowired
    private WorkerFactory workerFactory;

    @Autowired
    private Activity activity;

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(5))
//            .setMaximumInterval(Duration.ofSeconds(20))
            .setBackoffCoefficient(1)
            .setMaximumAttempts(10)
            .setDoNotRetry(NullPointerException.class.getName(), ClassCastException.class.getName())
            .build();

    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(2))
            .setRetryOptions(retryoptions)
            .setHeartbeatTimeout(Duration.ofSeconds(2))
            .build();

    WorkflowImplementationOptions options =
            WorkflowImplementationOptions.newBuilder()
                    .setFailWorkflowExceptionTypes(NullPointerException.class, ClassCastException.class)
                    .build();

    public void startMainWorker() {
        Worker worker = workerFactory.newWorker("1111111111111");

        worker.registerWorkflowImplementationTypes(
                options, WorkflowImpl.class
        );

        worker.registerActivitiesImplementations(
                activity
        );

        workerFactory.start();
    }
}
