package com.example.temporalhistoryanddebug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityImpl implements Activity {

    @Autowired
    private WorkFlowInfoRepository repository;
    @Override
    public String start() {
        double random = Math.random();
        System.out.println("============== random =====> " + random);
//        if (random * 10 < 2) {
////            return String.valueOf(io.temporal.activity.Activity.getExecutionContext().getInfo().getAttempt());
//            if (random * 10 < 2) {
//                throw new NullPointerException("NUllPOINTER");
//            }
//            throw new NullPointerException("CLASSCAST");
//        }
//        return String.valueOf(io.temporal.activity.Activity.getExecutionContext().getInfo().getAttempt());
        return "OK";
    }

    public String end() {
        double random = Math.random();
        System.out.println("============== random =====> " + random);
//        if (random * 10 < 2) {
//            throw new RuntimeException("FAILED");
//        }
//        throw new RuntimeException("FAILED");

        return "YES";
    }

    public void saveWorkflow(WorkflowLog log) {
        repository.insert(log);
    }
}
