package com.example.test_task_agencyamazon2.controller;

import com.example.test_task_agencyamazon2.model.Statistic;
import com.example.test_task_agencyamazon2.service.StatisticService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/by-date")
    public List<Statistic> getStatisticByDate(@RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate) {
        if (endDate == null) {
            return statisticService.getStatisticByDate(startDate);
        } else {
            return statisticService.getTotalStatisticsByDateRange(startDate, endDate);
        }
    }

    @GetMapping("/by-asin")
    public List<Statistic> getStatisticByAsin(@RequestParam List<String> asins) {
        if (asins.size() == 1) {
            return (statisticService.getStatisticByAsin(asins.get(0)));
        } else {
            return statisticService.getTotalStatisticsByAsinList(asins);
        }
    }

    @GetMapping("/total-by-date")
    public List<Statistic> getTotalStatisticsByDate() {
        return statisticService.getTotalStatisticsByDateRange(LocalDate.MIN, LocalDate.MAX);
    }

    @GetMapping("/total-by-asin")
    public List<Statistic> getTotalStatisticsByAsin() {
        return statisticService.getTotalStatisticsByAllAsin();
    }
}
