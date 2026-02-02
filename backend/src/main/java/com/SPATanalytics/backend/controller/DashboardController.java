package com.SPATanalytics.backend.controller;

import com.SPATanalytics.backend.model.DashboardStats;
import com.SPATanalytics.backend.service.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
// Global CORS already configured
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // GET /api/dashboard/stats
    // GET /api/dashboard/stats?date=2026-01-30
    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public DashboardStats getStats(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        if (date != null) {
            return dashboardService.getStatsByDate(date);
        }
        return dashboardService.getOverallStats();
    }
}
