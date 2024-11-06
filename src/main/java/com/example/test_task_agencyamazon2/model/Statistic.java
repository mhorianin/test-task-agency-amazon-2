package com.example.test_task_agencyamazon2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "statistic")
public class Statistic {
    @Id
    private String id;
    private LocalDate date;
    private SalesData salesByDate;
    private TrafficData trafficByDate;
    private String asin;
    private SalesData salesByAsin;
    private TrafficData trafficByAsin;
}
