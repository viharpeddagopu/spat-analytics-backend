package com.SPATanalytics.backend.service;

import com.SPATanalytics.backend.model.DashboardStats;
import com.SPATanalytics.backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final BookingRepository bookingRepository;

    public DashboardService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public DashboardStats getOverallStats() {
        DashboardStats stats = bookingRepository.getOverallStats();
        return stats != null
                ? stats
                : new DashboardStats(0L, 0.0, 0.0);
    }

    public DashboardStats getStatsByDate(LocalDate date) {
        DashboardStats stats = bookingRepository.getStatsByDate(date);
        return stats != null
                ? stats
                : new DashboardStats(0L, 0.0, 0.0);
    }
}
