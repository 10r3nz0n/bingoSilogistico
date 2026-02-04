package com.lorenzon.silogismos.domain;

import java.util.List;

public class ArgumentCard {

    private String id;
    private String name;
    private int figure;
    private String mood;
    private List<String> premises;
    private String conclusion;

    public ArgumentCard() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public List<String> getPremises() {
        return premises;
    }

    public void setPremises(List<String> premises) {
        this.premises = premises;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
