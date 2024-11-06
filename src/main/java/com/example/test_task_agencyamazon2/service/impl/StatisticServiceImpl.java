package com.example.test_task_agencyamazon2.service.impl;

import com.example.test_task_agencyamazon2.model.Statistic;
import com.example.test_task_agencyamazon2.repository.StatisticRepository;
import com.example.test_task_agencyamazon2.service.StatisticService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository repository;

    @Override
    @Cacheable(value = "statisticsByDate", key = "#date")
    public List<Statistic> getStatisticByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    @Override
    @Cacheable(value = "statisticsByAsin", key = "#asin")
    public List<Statistic> getStatisticByAsin(String asin) {
        return repository.findByAsin(asin);
    }

    @Override
    @Cacheable(value = "totalStatisticsByDateRange", key = "#startDate.toString() + '-' + #endDate.toString()")
    public List<Statistic> getTotalStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findAll().stream()
                .filter(stat -> !stat.getDate().isBefore(startDate) && !stat.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "totalStatisticsByAsinList", key = "#asinList")
    public List<Statistic> getTotalStatisticsByAsinList(List<String> asinList) {
        return repository.findAll().stream()
                .filter(stat -> asinList.contains(stat.getAsin()))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "totalStatisticsByAllAsin", key = "'allAsin'")
    public List<Statistic> getTotalStatisticsByAllAsin() {
        return repository.findAll().stream()
                .filter(stat -> stat.getAsin() != null)
                .toList();
    }
}
