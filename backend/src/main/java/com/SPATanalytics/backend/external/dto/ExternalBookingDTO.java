package com.SPATanalytics.backend.external.dto;

import java.time.LocalDate;

public class ExternalBookingDTO {

    private String ticketNumber;
    private LocalDate bookingDate;
    private String companyName;
    private Double ticketAmount;
    private Double commission;

    public ExternalBookingDTO() {}

    public ExternalBookingDTO(
            String ticketNumber,
            LocalDate bookingDate,
            String companyName,
            Double ticketAmount,
            Double commission
    ) {
        this.ticketNumber = ticketNumber;
        this.bookingDate = bookingDate;
        this.companyName = companyName;
        this.ticketAmount = ticketAmount;
        this.commission = commission;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Double getTicketAmount() {
        return ticketAmount;
    }

    public Double getCommission() {
        return commission;
    }
}
