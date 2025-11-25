package com.example.employeeManagement.enums;

import lombok.Getter;

@Getter
public enum BonusRate {
    WINTER("winter", 1.3),
    AUTUMN("autumn", 0.4),
    SPRING("spring", 0.6),
    SUMMER("summer", 0.7);

    private final String season;
    private final Double rate;

    BonusRate(String season, Double rate) {
        this.season = season;
        this.rate = rate;
    }

    public static Double getRateBySeason(String season) {
        for (BonusRate bonusRate : values()) {
            if (bonusRate.getSeason().equalsIgnoreCase(season)) {
                return bonusRate.getRate();
            }
        }
        throw new IllegalArgumentException("Invalid season: " + season);  // handle invalid season input
    }
}
