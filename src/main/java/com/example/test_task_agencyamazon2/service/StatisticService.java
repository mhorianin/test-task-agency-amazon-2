package com.example.test_task_agencyamazon2.service;

import com.example.test_task_agencyamazon2.model.Statistic;
import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    List<Statistic> getStatisticByDate(LocalDate date);
    List<Statistic> getStatisticByAsin(String asin);
    List<Statistic> getTotalStatisticsByDateRange(LocalDate startDate, LocalDate endDate);
    List<Statistic> getTotalStatisticsByAsinList(List<String> asinList);
    List<Statistic> getTotalStatisticsByAllAsin();

}
