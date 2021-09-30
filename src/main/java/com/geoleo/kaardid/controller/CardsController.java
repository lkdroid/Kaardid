package com.geoleo.kaardid.controller;

import com.geoleo.kaardid.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@RestController
public class CardsController {

    @Autowired
    private CardsService cardsService;


    @GetMapping("uniquename/{name}")
    public Boolean uniqueName(@PathVariable("name") String name) {

        return cardsService.uniqueName(name);

    }

    @GetMapping("insertname/{name}")
    public Integer insertName(@PathVariable("name") String name) {
        return cardsService.insertName(name);

    }

    @GetMapping("creategame/{playerId}/{gameType}")
    public UUID createGame(@PathVariable("playerId") Integer firstPlayerID,
                           @PathVariable("gameType") Boolean gameType) {
        return cardsService.createGame(firstPlayerID, gameType);
    }

    @GetMapping("checkgame/{playerID}/{gameType}")
    public UUID checkGame(@PathVariable("playerID") Integer ID,
                          @PathVariable("gameType") Boolean gametype) {
        return cardsService.checkGame(ID, gametype);

    }

    @GetMapping("joingame/{playerId}/{gameId}")
    public void joinGame(@PathVariable("playerId") Integer playerId, @PathVariable("gameId") UUID gameId) {
        cardsService.joinGame(playerId, gameId);
    }

    @GetMapping("checkready/{gameId}")
    public boolean checkReady(@PathVariable("gameId") UUID gameId) {
        return cardsService.checkReady(gameId);
    }

    @GetMapping("checkNameId/{name}/{playerId}")
    public boolean checkNameId(@PathVariable("name") String name, @PathVariable("playerId") int playerId) {
        return cardsService.checkNameId(name, playerId);

    }

    @GetMapping("checkwhoisfirst/{gameId}/{playerId}")
    public Boolean checkWhoIsFirst(@PathVariable("gameId") UUID gameId,
                                   @PathVariable("playerId") Integer playerId) {
        return cardsService.checkWhoIsFirst(gameId, playerId);

    }

    @GetMapping("randomcards/{gameId}")
    public void randomCardsInGame(@PathVariable("gameId") UUID gameId) {
        cardsService.randomCardsInGame(gameId);
    }

    @GetMapping("choose1card/{gameId}/{cardcount}")
    public int choose1card(@PathVariable("cardcount") int cardCount, @PathVariable("gameId") UUID gameId) {
        return cardsService.choose1card(gameId, cardCount);
    }

    @GetMapping("countrydata/{id}")
    public CountriesList allCountryData(@PathVariable("id") Integer countryId) {
        return cardsService.allCountryData(countryId);
    }


    public static class countriesListRowMapper implements RowMapper<CountriesList> {

        @Override
        public CountriesList mapRow(ResultSet resultSet, int i) throws SQLException {
            CountriesList result = new CountriesList();
            result.setId(resultSet.getInt("countries_id"));
            result.setCountryName(resultSet.getString("country_name"));
            result.setCapital(resultSet.getString("capital"));
            result.setPopulation(resultSet.getInt("population"));
            result.setArea(resultSet.getInt("area"));
            result.setHdi(resultSet.getDouble("hdi"));
            result.setWater(resultSet.getDouble("water"));
            result.setDensity(resultSet.getDouble("density"));
            result.setAvgHeight(resultSet.getInt("avgheight"));
            result.setHighestPointNumber(resultSet.getInt("highestpoint"));
            result.setLowestPointNumber(resultSet.getInt("lowestpoint"));
            result.setCountryCode(resultSet.getString("country_code"));
            result.setHighestName(resultSet.getString("highest_name"));
            result.setLowestName(resultSet.getString("lowest_name"));
            result.setFlagColors(resultSet.getInt("flag_colors"));
            result.setContourCode(resultSet.getString("contour_code"));
            return result;
        }
    }

}













