package com.example.test_task_agencyamazon2.repository;

import com.example.test_task_agencyamazon2.model.Statistic;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    List<Statistic> findByDate(LocalDate date);
    List<Statistic> findByAsin(String asin);
}
