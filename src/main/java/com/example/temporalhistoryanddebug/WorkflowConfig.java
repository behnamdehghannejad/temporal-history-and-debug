package com.example.temporalhistoryanddebug;

import io.grpc.ManagedChannelBuilder;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class WorkflowConfig {

    @Value("${temporal.server.port}")
    private int temporalServicePort;
    @Value("${temporal.server.address}")
    private String temporalServiceAddress;
    @Value("${temporal.name.space}")
    private String temporalNamespace;


    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        var channel = ManagedChannelBuilder.forAddress(temporalServiceAddress, temporalServicePort).usePlaintext().build();
        return WorkflowServiceStubs.
                newInstance(WorkflowServiceStubsOptions.newBuilder()
                        .setChannel(channel)
                        .build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder()
                        .setNamespace(temporalNamespace)
                        .build());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }
}