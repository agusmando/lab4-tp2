package com.TPGrupalLab4.JavaPostgre.model;

public enum YesNoEnum {
    SI("Sí"),
    NO("No");

    private final String label;

    YesNoEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
