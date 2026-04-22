package com.budgetpath.controller;

import com.budgetpath.model.Place;
import com.budgetpath.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<Place>> findAll() {
        return ResponseEntity.ok(placeService.findAll());
    }

    @GetMapping("/region")
    public ResponseEntity<List<Place>> findByRegion(@RequestParam String name) {
        return ResponseEntity.ok(placeService.findOrFetchByRegion(name));
    }

    @PostMapping
    public ResponseEntity<Place> create(@RequestBody Place place) {
        return ResponseEntity.ok(placeService.save(place));
    }
}
