package com.fssa.veeblooms.enumclass;

public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GenderEnum fromValue(String value) {
        for (GenderEnum gender : values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        // Handle the case where the input value doesn't match any enum constant.
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
