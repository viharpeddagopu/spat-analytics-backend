package com.SPATanalytics.backend.external.source;

import com.SPATanalytics.backend.external.BookingSource;
import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("csvSource")
public class CsvBookingSource implements BookingSource {

    private static final String CSV_FILE_PATH =
            "data/bookings.csv"; // adjust path as needed

    @Override
    public List<ExternalBookingDTO> fetchBookings() {

        List<ExternalBookingDTO> bookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(CSV_FILE_PATH))) {

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                ExternalBookingDTO dto = new ExternalBookingDTO(
                        fields[0].trim(),
                        LocalDate.parse(fields[1].trim()),
                        fields[2].trim(),
                        Double.parseDouble(fields[3].trim()),
                        Double.parseDouble(fields[4].trim())
                );

                bookings.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }

        return bookings;
    }
}
