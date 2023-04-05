package com.example.temporalhistoryanddebug;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Activity {

    @ActivityMethod
    String start();

    @ActivityMethod
    String end();

    @ActivityMethod
    void saveWorkflow(WorkflowLog log);
}
