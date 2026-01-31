package com.SPATanalytics.backend.repository;

import com.SPATanalytics.backend.model.Booking;
import com.SPATanalytics.backend.model.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByBookingDate(LocalDate bookingDate);

    List<Booking> findByCompanyId(Long companyId);

    Optional<Booking> findByTicketNumber(String ticketNumber);

    // Overall dashboard stats
    @Query("""
        SELECT new com.SPATanalytics.backend.model.DashboardStats(
            COUNT(b),
            COALESCE(SUM(b.ticketAmount), 0),
            COALESCE(SUM(b.commission), 0)
        )
        FROM Booking b
    """)
    DashboardStats getOverallStats();

    // Date-wise dashboard stats
    @Query("""
        SELECT new com.SPATanalytics.backend.model.DashboardStats(
            COUNT(b),
            COALESCE(SUM(b.ticketAmount), 0),
            COALESCE(SUM(b.commission), 0)
        )
        FROM Booking b
        WHERE b.bookingDate = :date
    """)
    DashboardStats getStatsByDate(@Param("date") LocalDate date);
}
