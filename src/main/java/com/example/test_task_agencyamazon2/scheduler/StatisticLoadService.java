package com.example.test_task_agencyamazon2.scheduler;

import com.example.test_task_agencyamazon2.model.SalesData;
import com.example.test_task_agencyamazon2.model.Statistic;
import com.example.test_task_agencyamazon2.model.TrafficData;
import com.example.test_task_agencyamazon2.repository.StatisticRepository;
import com.mongodb.lang.Nullable;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticLoadService {
    private final StatisticRepository repository;
    private final ResourceLoader resourceLoader;

    @Scheduled(fixedRate = 300000)
    public void loadStatistics() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(resourceLoader.getResource("classpath:test_report.json").getFile());
        JSONObject root = (JSONObject) jsonParser.parse(reader);

        List<Statistic> statistics = new ArrayList<>();

        JSONArray salesAndTrafficByDate = (JSONArray) root.get("salesAndTrafficByDate");
        if (salesAndTrafficByDate != null) {
            for (Object dateObj : salesAndTrafficByDate) {
                JSONObject dateNode = (JSONObject) dateObj;

                Statistic statistic = new Statistic();
                statistic.setDate(LocalDate.parse((String) dateNode.get("date")));
                statistic.setSalesByDate(parseSalesData((JSONObject) dateNode.get("salesByDate")));
                statistic.setTrafficByDate(parseTrafficData((JSONObject) dateNode.get("trafficByDate")));

                statistics.add(statistic);
            }
        }

        JSONArray salesAndTrafficByAsin = (JSONArray) root.get("salesAndTrafficByAsin");
        if (salesAndTrafficByAsin != null) {
            for (Object asinObj : salesAndTrafficByAsin) {
                JSONObject asinNode = (JSONObject) asinObj;

                Statistic statistic = new Statistic();
                statistic.setAsin((String) asinNode.get("parentAsin"));
                statistic.setSalesByAsin(parseSalesData((JSONObject) asinNode.get("salesByAsin")));
                statistic.setTrafficByAsin(parseTrafficData((JSONObject) asinNode.get("trafficByAsin")));

                statistics.add(statistic);
            }
        }

        repository.saveAll(statistics);
    }

    private SalesData parseSalesData(@Nullable JSONObject salesNode) {
        if (salesNode == null) return null;

        SalesData salesData = new SalesData();
        salesData.setOrderedProductSales(getDoubleFromJSONObject(salesNode, "orderedProductSales"));
        salesData.setOrderedProductSalesB2B(getDoubleFromJSONObject(salesNode, "orderedProductSalesB2B"));
        salesData.setUnitsOrdered(getIntFromJSONObject(salesNode, "unitsOrdered"));
        salesData.setUnitsOrderedB2B(getIntFromJSONObject(salesNode, "unitsOrderedB2B"));
        salesData.setTotalOrderItems(getIntFromJSONObject(salesNode, "totalOrderItems"));
        salesData.setTotalOrderItemsB2B(getIntFromJSONObject(salesNode, "totalOrderItemsB2B"));
        salesData.setUnitsRefunded(getIntFromJSONObject(salesNode, "unitsRefunded"));
        salesData.setRefundRate(getDoubleFromJSONObject(salesNode, "refundRate"));
        salesData.setShippedProductSales(getDoubleFromJSONObject(salesNode, "shippedProductSales"));
        salesData.setUnitsShipped(getIntFromJSONObject(salesNode, "unitsShipped"));
        salesData.setOrdersShipped(getIntFromJSONObject(salesNode, "ordersShipped"));

        return salesData;
    }

    private TrafficData parseTrafficData(@Nullable JSONObject trafficNode) {
        if (trafficNode == null) return null;

        TrafficData trafficData = new TrafficData();
        trafficData.setBrowserPageViews(getIntFromJSONObject(trafficNode, "browserPageViews"));
        trafficData.setMobileAppPageViews(getIntFromJSONObject(trafficNode, "mobileAppPageViews"));
        trafficData.setPageViews(getIntFromJSONObject(trafficNode, "pageViews"));
        trafficData.setBrowserSessions(getIntFromJSONObject(trafficNode, "browserSessions"));
        trafficData.setMobileAppSessions(getIntFromJSONObject(trafficNode, "mobileAppSessions"));
        trafficData.setSessions(getIntFromJSONObject(trafficNode, "sessions"));
        trafficData.setBuyBoxPercentage(getDoubleFromJSONObject(trafficNode, "buyBoxPercentage"));
        trafficData.setUnitSessionPercentage(getDoubleFromJSONObject(trafficNode, "unitSessionPercentage"));

        return trafficData;
    }

    private double getDoubleFromJSONObject(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0;
    }

    private int getIntFromJSONObject(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0;
    }
}
