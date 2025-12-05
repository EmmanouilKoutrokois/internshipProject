package com.example.employeeManagement.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VacationStatus {
    PENDING("pending approval"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String label;

    VacationStatus(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static VacationStatus fromValue(String value) {
        for (VacationStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

    public String toUpperCase() {
        return null;
    }

    public boolean isBlank() {
        return false;
    }

    public VacationStatus trim() {
        return null;
    }
}
