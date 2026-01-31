package com.SPATanalytics.backend.external.api;

import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mock-api/bookings")
public class MockBookingApiController {

    @GetMapping
    public List<ExternalBookingDTO> getBookings() {
        List<ExternalBookingDTO> bookings = new ArrayList<>();

        Path path = Path.of("src/main/resources/data/bookings.csv");

        if (!Files.exists(path)) {
            return bookings; // empty list, no crash
        }

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                // SAFETY CHECK
                if (parts.length < 5) continue;

                bookings.add(
                        new ExternalBookingDTO(
                                parts[0].trim(),
                                LocalDate.parse(parts[1].trim()),
                                parts[2].trim(),
                                Double.parseDouble(parts[3].trim()),
                                Double.parseDouble(parts[4].trim())
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookings;
    }
}
