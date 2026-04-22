package com.eolmatrip.controller;

import com.eolmatrip.dto.TripRequest;
import com.eolmatrip.dto.TripResponse;
import com.eolmatrip.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TripController {

    private final TripService tripService;

    @PostMapping("/generate")
    public ResponseEntity<TripResponse> generate(@Valid @RequestBody TripRequest request) {
        return ResponseEntity.ok(tripService.generate(request));
    }
}
