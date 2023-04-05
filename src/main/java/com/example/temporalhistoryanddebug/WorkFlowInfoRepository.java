package com.example.temporalhistoryanddebug;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkFlowInfoRepository extends MongoRepository<WorkflowLog, String> {
}
