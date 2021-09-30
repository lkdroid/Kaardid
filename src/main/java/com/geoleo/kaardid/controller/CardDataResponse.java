package com.geoleo.kaardid.controller;

import com.geoleo.kaardid.service.Country;

public class CardDataResponse {
    private Country card1;
    private Country card2;

    public Country getCard1() {
        return card1;
    }

    public void setCard1(Country card1) {
        this.card1 = card1;
    }

    public Country getCard2() {
        return card2;
    }

    public void setCard2(Country card2) {
        this.card2 = card2;
    }
}
