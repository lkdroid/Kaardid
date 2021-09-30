package com.geoleo.kaardid.controller;

public class PollResponse {
    private Boolean result;
    private String chosenType;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getChosenType() {
        return chosenType;
    }

    public void setChosenType(String chosenType) {
        this.chosenType = chosenType;
    }
}
