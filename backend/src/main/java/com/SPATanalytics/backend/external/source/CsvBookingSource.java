package com.SPATanalytics.backend.external.source;

import com.SPATanalytics.backend.external.BookingSource;
import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("csvSource")
public class CsvBookingSource implements BookingSource {

    @Override
    public List<ExternalBookingDTO> fetchBookings() {

        List<ExternalBookingDTO> bookings = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new ClassPathResource("data/bookings.csv").getInputStream()
                        )
                )
        ) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                // Skip header
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                // Safety check
                if (parts.length < 5) continue;

                String ticketNumber = parts[0].trim();
                int daysAgo = Integer.parseInt(parts[1].trim());
                String companyName = parts[2].trim();
                double ticketAmount = Double.parseDouble(parts[3].trim());
                double commission = Double.parseDouble(parts[4].trim());

                LocalDate bookingDate = LocalDate.now().plusDays(daysAgo);

                bookings.add(new ExternalBookingDTO(
                        ticketNumber,
                        bookingDate,
                        companyName,
                        ticketAmount,
                        commission
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }

        return bookings;
    }
}