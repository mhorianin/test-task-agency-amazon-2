package com.example.test_task_agencyamazon2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficData {
    private int browserPageViews;
    private int mobileAppPageViews;
    private int pageViews;
    private int browserSessions;
    private int mobileAppSessions;
    private int sessions;
    private double buyBoxPercentage;
    private double unitSessionPercentage;
}
