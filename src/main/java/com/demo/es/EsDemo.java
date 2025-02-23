package com.demo.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(indexName = "demo")
@Data
public class EsDemo {

    @Id
    private Integer demoId;

    private String demoName;

    private LocalDateTime createTime;

}
