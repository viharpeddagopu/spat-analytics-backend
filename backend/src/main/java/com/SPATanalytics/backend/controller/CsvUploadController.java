package com.SPATanalytics.backend.controller;

import com.SPATanalytics.backend.service.BookingIngestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/upload")
public class CsvUploadController {

    private final BookingIngestionService ingestionService;

    public CsvUploadController(BookingIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping(
            value = "/bookings",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<String> uploadCsv(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "csvText", required = false) String csvText
    ) {
        try {
            String dataToWrite = null;

            if (file != null && !file.isEmpty()) {
                dataToWrite = new String(file.getBytes());
            }
            else if (csvText != null && !csvText.trim().isEmpty()) {
                dataToWrite = csvText;
            }

            if (dataToWrite == null) {
                return ResponseEntity.badRequest()
                        .body("No CSV data provided");
            }

            Path path = Path.of("src/main/resources/data/bookings.csv");

            Files.createDirectories(path.getParent());

            Files.writeString(path, dataToWrite);

            ingestionService.ingestFromMock();

            return ResponseEntity.ok("CSV data ingested successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Failed to ingest CSV data");
        }
    }

}
