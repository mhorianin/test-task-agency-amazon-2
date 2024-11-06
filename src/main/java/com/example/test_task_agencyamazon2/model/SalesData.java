package com.example.test_task_agencyamazon2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesData {
    private double orderedProductSales;
    private double orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private double averageSalesPerOrderItem;
    private double averageSalesPerOrderItemB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private double shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
