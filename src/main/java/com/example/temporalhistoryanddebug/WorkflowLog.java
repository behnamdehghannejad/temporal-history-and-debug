package com.example.temporalhistoryanddebug;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowLog {

    @Id
    private String id;

    private String workflowInfo;

    private String history;

}


