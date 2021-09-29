package com.geoleo.kaardid.controller;

public class CountriesList {

    private Integer id;
    private String countryName;
    private String capital;
    private Integer population;
    private Integer area;
    private Double hdi;
    private Double water;
    private Double density;
    private Integer avgHeight;
    private Integer highestPointNumber;
    private Integer lowestPointNumber;
    private Integer cCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Double getHdi() {
        return hdi;
    }

    public void setHdi(Double hdi) {
        this.hdi = hdi;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Integer getAvgHeight() {
        return avgHeight;
    }

    public void setAvgHeight(Integer avgHeight) {
        this.avgHeight = avgHeight;
    }

    public Integer getHighestPointNumber() {
        return highestPointNumber;
    }

    public void setHighestPointNumber(Integer highestPointNumber) {
        this.highestPointNumber = highestPointNumber;
    }

    public Integer getLowestPointNumber() {
        return lowestPointNumber;
    }

    public void setLowestPointNumber(Integer lowestPointNumber) {
        this.lowestPointNumber = lowestPointNumber;
    }

    public Integer getcCode() {
        return cCode;
    }

    public void setcCode(Integer cCode) {
        this.cCode = cCode;
    }
}
