package com.SPATanalytics.backend.model;

public class DashboardStats {

    private Long totalTickets;
    private Double totalAmount;
    private Double totalCommission;

    public DashboardStats(Long totalTickets, Double totalAmount, Double totalCommission) {
        this.totalTickets = totalTickets;
        this.totalAmount = totalAmount;
        this.totalCommission = totalCommission;
    }

    public Long getTotalTickets() {
        return totalTickets;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getTotalCommission() {
        return totalCommission;
    }
}
