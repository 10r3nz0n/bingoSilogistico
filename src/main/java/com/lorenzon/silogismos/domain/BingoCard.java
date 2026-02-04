package com.lorenzon.silogismos.domain;

import java.util.List;

public class BingoCard {

    private int number;
    private List<String> modes;

    public BingoCard() {
    }

    public BingoCard(int number, List<String> modes) {
        this.number = number;
        this.modes = modes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setModes(List<String> modes) {
        this.modes = modes;
    }
}
