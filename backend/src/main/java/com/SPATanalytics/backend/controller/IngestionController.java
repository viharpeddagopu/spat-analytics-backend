package com.SPATanalytics.backend.controller;

import com.SPATanalytics.backend.service.BookingIngestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingestion")
public class IngestionController {

    private final BookingIngestionService ingestionService;

    public IngestionController(BookingIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/mock")
    public String ingestFromMock() {
        ingestionService.ingestFromMock();
        return "Mock API ingestion complete";
    }

    @PostMapping("/csv")
    public String ingestFromCsv() {
        ingestionService.ingestFromCsv();
        return "CSV ingestion complete";
    }
}
