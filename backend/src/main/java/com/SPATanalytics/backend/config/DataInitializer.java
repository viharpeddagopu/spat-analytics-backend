package com.SPATanalytics.backend.config;

import com.SPATanalytics.backend.repository.BookingRepository;
import com.SPATanalytics.backend.repository.CompanyRepository;
import com.SPATanalytics.backend.service.BookingIngestionService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookingRepository bookingRepository;
    private final CompanyRepository companyRepository;
    private final BookingIngestionService bookingIngestionService;

    public DataInitializer(
            BookingRepository bookingRepository,
            CompanyRepository companyRepository,
            BookingIngestionService bookingIngestionService
    ) {
        this.bookingRepository = bookingRepository;
        this.companyRepository = companyRepository;
        this.bookingIngestionService = bookingIngestionService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Resetting database and seeding fresh CSV data...");

        // IMPORTANT: order matters (FK constraints)
        bookingRepository.deleteAll();
        companyRepository.deleteAll();

        bookingIngestionService.ingestFromCsv();

        System.out.println("CSV data seeded successfully.");
    }
}