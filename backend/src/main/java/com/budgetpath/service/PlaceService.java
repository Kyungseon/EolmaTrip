package com.budgetpath.service;

import com.budgetpath.model.Place;
import com.budgetpath.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> findAll() {
        log.info("[Repository] PlaceRepository.findAll()");
        return placeRepository.findAll();
    }

    public Place save(Place place) {
        log.info("[Repository] PlaceRepository.save()");
        return placeRepository.save(place);
    }

    public List<Place> findByRegion(String region) {
        log.info("[Repository] PlaceRepository.findByRegion({})", region);
        return placeRepository.findByRegion(region);
    }
}
