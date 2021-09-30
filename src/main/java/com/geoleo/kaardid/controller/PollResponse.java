package com.geoleo.kaardid.controller;

public class PollResponse {
    private Boolean result;
    private String chosenField;
    private Integer winner;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getChosenField() {
        return chosenField;
    }

    public void setChosenField(String chosenField) {
        this.chosenField = chosenField;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }
}
