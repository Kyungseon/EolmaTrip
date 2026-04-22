package com.budgetpath.algorithm;

import com.budgetpath.dto.TripRequest;
import com.budgetpath.model.Place;

import java.util.*;

/**
 * Greedy knapsack + nearest-neighbor TSP 조합
 * 예산 내에서 점수 합이 최대인 장소 집합을 선택한 뒤 동선을 정렬한다.
 */
public class TripOptimizer {

    public List<Place> optimize(List<Place> candidates, TripRequest req) {
        List<Place> weighted = applyPreferenceWeights(candidates, req.getPreferences());
        List<Place> selected = knapsackSelect(weighted, req.getBudget(), req.getDurationDays());
        return nearestNeighborSort(selected);
    }

    // 카테고리 가중치 반영: place.score *= preference
    private List<Place> applyPreferenceWeights(List<Place> places, TripRequest.Preferences prefs) {
        for (Place p : places) {
            double weight = switch (p.getCategory()) {
                case food       -> prefs.getFood();
                case hotel      -> prefs.getHotel();
                case experience -> prefs.getExperience();
            };
            p.setScore((float) (p.getScore() * weight));
        }
        return places;
    }

    // 그리디 Knapsack: score/price 내림차순으로 예산 소진
    private List<Place> knapsackSelect(List<Place> places, int budget, int durationDays) {
        int maxMinutes = durationDays * 8 * 60; // 하루 8시간 기준

        places.sort(Comparator.comparingDouble((Place p) -> p.getScore() / Math.max(p.getPrice(), 1)).reversed());

        List<Place> result = new ArrayList<>();
        int remainBudget  = budget;
        int remainMinutes = maxMinutes;
        boolean hotelPicked = false;

        for (Place p : places) {
            // 호텔은 1개만
            if (p.getCategory() == Place.Category.hotel) {
                if (hotelPicked) continue;
                hotelPicked = true;
            }
            if (p.getPrice() <= remainBudget && p.getDurationMinutes() <= remainMinutes) {
                result.add(p);
                remainBudget  -= p.getPrice();
                remainMinutes -= p.getDurationMinutes();
            }
        }
        return result;
    }

    // 최근접 이웃 알고리즘으로 동선 정렬
    private List<Place> nearestNeighborSort(List<Place> places) {
        if (places.size() <= 1) return places;

        List<Place> sorted  = new ArrayList<>();
        List<Place> remaining = new ArrayList<>(places);

        // 호텔을 첫 번째로 고정
        Place start = remaining.stream()
                .filter(p -> p.getCategory() == Place.Category.hotel)
                .findFirst()
                .orElse(remaining.get(0));
        remaining.remove(start);
        sorted.add(start);

        while (!remaining.isEmpty()) {
            Place current = sorted.get(sorted.size() - 1);
            Place nearest = remaining.stream()
                    .min(Comparator.comparingDouble(p -> haversine(current, p)))
                    .orElseThrow();
            sorted.add(nearest);
            remaining.remove(nearest);
        }
        return sorted;
    }

    private double haversine(Place a, Place b) {
        final double R = 6371;
        double dLat = Math.toRadians(b.getLat() - a.getLat());
        double dLng = Math.toRadians(b.getLng() - a.getLng());
        double h = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat()))
                 * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        return R * 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
    }
}
