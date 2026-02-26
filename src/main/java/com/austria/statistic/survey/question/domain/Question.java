package com.austria.statistic.survey.question.domain;

import java.util.ArrayList;
import java.util.List;


public class Question {

    private Long id;
    private final String text;
    private final QuestionType type;
    private final int position;
    private boolean required;

    private final List<AnswerOption> options = new ArrayList<>();

    private Question(String text, QuestionType type, int position) {
        this.text = text;
        this.type = type;
        this.position = position;
        this.required = true;
    }

    public static Question create(String text, QuestionType type, int position) {
        return new Question(text, type, position);
    }

    public void addOption(String label, String value, int position) {

        if (type == QuestionType.TEXT)
            throw new IllegalStateException("Text question cannot have options");

        options.add(new AnswerOption(label, value, position));
    }
    
    public void isRequiredFalse() {
    	this.required = false;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public QuestionType getType() {
		return type;
	}

	public int getPosition() {
		return position;
	}
    
	public boolean getRequired() {
		return required;
	}
    
}