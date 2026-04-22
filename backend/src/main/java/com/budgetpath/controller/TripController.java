package com.budgetpath.controller;

import com.budgetpath.dto.TripRequest;
import com.budgetpath.dto.TripResponse;
import com.budgetpath.service.TripService;
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
