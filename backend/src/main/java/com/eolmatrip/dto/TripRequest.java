package com.eolmatrip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TripRequest {

    @Min(1)
    private int budget;

    @NotBlank
    private String region;

    @Min(1)
    private int durationDays;

    @NotNull
    private Preferences preferences;

    @Getter @Setter
    public static class Preferences {
        private double food;
        private double hotel;
        private double experience;
    }
}
