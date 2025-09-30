package com.applico.core.domain.model.statics;

public enum ExperienceLevel {
    INTERN("Intern"),
    JUNIOR("Junior"),
    MID_LEVEL("Mid-level"),
    SENIOR("Senior"),
    STAFF("Staff");

    private final String label;

    ExperienceLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
