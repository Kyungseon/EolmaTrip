package com.budgetpath.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class TripResponse {
    private String region;
    private int totalBudget;
    private int usedBudget;
    private int durationDays;
    private double totalScore;
    private List<PlaceItem> itinerary;

    @Getter @Builder
    public static class PlaceItem {
        private Long id;
        private String name;
        private String category;
        private int price;
        private float rating;
        private int durationMinutes;
        private double score;
        private double lat;
        private double lng;
    }
}
