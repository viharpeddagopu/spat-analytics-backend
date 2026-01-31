package com.SPATanalytics.backend.external.source;

import com.SPATanalytics.backend.external.BookingSource;
import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component("mockApiSource")
public class MockApiBookingSource implements BookingSource {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<ExternalBookingDTO> fetchBookings() {

        ExternalBookingDTO[] response =
                restTemplate.getForObject(
                        "http://localhost:8080/mock-api/bookings",
                        ExternalBookingDTO[].class
                );

        return Arrays.asList(response);
    }
}
