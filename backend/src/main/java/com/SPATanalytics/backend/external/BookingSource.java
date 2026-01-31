package com.SPATanalytics.backend.external;

import com.SPATanalytics.backend.external.dto.ExternalBookingDTO;

import java.util.List;

public interface BookingSource {

    List<ExternalBookingDTO> fetchBookings();
}
