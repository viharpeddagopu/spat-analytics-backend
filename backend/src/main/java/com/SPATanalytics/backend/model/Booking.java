package com.SPATanalytics.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//Lombok is not working
@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "ticketNumber")
        }
)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ticketNumber;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private Double ticketAmount;

    @Column(nullable = false)
    private Double commission;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Booking() {}

    public Long getId() {
        return id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public Double getTicketAmount() {
        return ticketAmount;
    }

    public Double getCommission() {
        return commission;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTicketAmount(Double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
