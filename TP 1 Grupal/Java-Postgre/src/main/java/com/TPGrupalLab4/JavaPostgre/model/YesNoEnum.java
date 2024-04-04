package com.TPGrupalLab4.JavaPostgre.model;

public enum YesNoEnum {
    Y("Y"),
    N("N");

    private final String label;

    YesNoEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
