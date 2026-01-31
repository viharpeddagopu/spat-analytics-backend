package com.SPATanalytics.backend.service;

import com.SPATanalytics.backend.external.BookingSource;
import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;
import com.SPATanalytics.backend.model.Booking;
import com.SPATanalytics.backend.model.Company;
import com.SPATanalytics.backend.repository.BookingRepository;
import com.SPATanalytics.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingIngestionService {

    private final BookingSource mockApiSource;
    private final BookingSource csvSource;

    private final BookingRepository bookingRepository;
    private final CompanyRepository companyRepository;

    public BookingIngestionService(
            @Qualifier("mockApiSource") BookingSource mockApiSource,
            @Qualifier("csvSource") BookingSource csvSource,
            BookingRepository bookingRepository,
            CompanyRepository companyRepository
    ) {
        this.mockApiSource = mockApiSource;
        this.csvSource = csvSource;
        this.bookingRepository = bookingRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void ingestFromMock() {
        ingest(mockApiSource);
    }

    @Transactional
    public void ingestFromCsv() {
        ingest(csvSource);
    }

    private void ingest(BookingSource source) {

        List<ExternalBookingDTO> bookings = source.fetchBookings();

        for (ExternalBookingDTO dto : bookings) {

            Company company = companyRepository
                    .findByName(dto.getCompanyName())
                    .orElseGet(() -> {
                        Company c = new Company();
                        c.setName(dto.getCompanyName());
                        return companyRepository.save(c);
                    });

            Booking booking = bookingRepository
                    .findByTicketNumber(dto.getTicketNumber())
                    .orElseGet(Booking::new);

            // 🔹 INSERT or UPDATE (same code path)
            booking.setTicketNumber(dto.getTicketNumber());
            booking.setBookingDate(dto.getBookingDate());
            booking.setTicketAmount(dto.getTicketAmount());
            booking.setCommission(dto.getCommission());
            booking.setCompany(company);

            bookingRepository.save(booking);
        }
    }
}