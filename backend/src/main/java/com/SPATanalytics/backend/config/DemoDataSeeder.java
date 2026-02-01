package com.SPATanalytics.backend.config;

import com.SPATanalytics.backend.repository.BookingRepository;
import com.SPATanalytics.backend.service.BookingIngestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DemoDataSeeder {

    private final BookingRepository bookingRepository;
    private final BookingIngestionService ingestionService;

    public DemoDataSeeder(
            BookingRepository bookingRepository,
            BookingIngestionService ingestionService
    ) {
        this.bookingRepository = bookingRepository;
        this.ingestionService = ingestionService;
    }

    @Bean
    CommandLineRunner seedDemoData() {
        return args -> {
            long count = bookingRepository.count();

            if (count == 0) {
                System.out.println("No bookings found. Seeding demo data from CSV...");
                ingestionService.ingestFromCsv();
            } else {
                System.out.println("Bookings already exist. Skipping demo seeding.");
            }
        };
    }
}
