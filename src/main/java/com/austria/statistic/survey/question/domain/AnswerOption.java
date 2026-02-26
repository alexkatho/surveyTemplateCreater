package com.austria.statistic.survey.question.domain;

public class AnswerOption {

    private Long id;
    private final String label;
    private final String value;
    private final int position;

    public AnswerOption(String label, String value, int position) {
        if (label == null || label.isBlank())
            throw new IllegalArgumentException("Label required");

        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Value required");

        this.label = label;
        this.value = value;
        this.position = position;
    }

    public Long getId() { return id; }
    public String getLabel() { return label; }
    public String getValue() { return value; }
    public int getPosition() { return position; }
}
