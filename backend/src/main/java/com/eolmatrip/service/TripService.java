package com.eolmatrip.service;

import com.eolmatrip.algorithm.TripOptimizer;
import com.eolmatrip.dto.TripRequest;
import com.eolmatrip.dto.TripResponse;
import com.eolmatrip.model.Place;
import com.eolmatrip.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final PlaceRepository placeRepository;
    private final TripOptimizer tripOptimizer;

    public TripResponse generate(TripRequest req) {
        log.info("[Repository] PlaceRepository.findAffordable({}, {})", req.getRegion(), req.getBudget());
        List<Place> candidates = placeRepository.findAffordable(req.getRegion(), req.getBudget());
        List<Place> itinerary  = tripOptimizer.optimize(candidates, req);

        int usedBudget    = itinerary.stream().mapToInt(Place::getPrice).sum();
        double totalScore = itinerary.stream().mapToDouble(Place::getScore).sum();

        List<TripResponse.PlaceItem> items = itinerary.stream()
                .map(p -> TripResponse.PlaceItem.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .category(p.getCategory().name())
                        .price(p.getPrice())
                        .rating(p.getRating())
                        .durationMinutes(p.getDurationMinutes())
                        .score(p.getScore())
                        .lat(p.getLat())
                        .lng(p.getLng())
                        .build())
                .toList();

        return TripResponse.builder()
                .region(req.getRegion())
                .totalBudget(req.getBudget())
                .usedBudget(usedBudget)
                .durationDays(req.getDurationDays())
                .totalScore(totalScore)
                .itinerary(items)
                .build();
    }
}
