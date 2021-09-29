package com.geoleo.kaardid.controller;

import com.geoleo.kaardid.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("creategame/{playerId}/{gameType}")
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


    @GetMapping("countrydata/{id}")
    public CountriesList allCountryData(@PathVariable("id") Integer countryId) {
        return cardsService.allCountryData(countryId);
    }

    @PostMapping("cardsInGameAdding/{playerId}/{gameId}/{countryId}")
    public void cardsInGameAdding(@PathVariable("playerId") Integer playerId,
                                  @PathVariable("gameId") UUID gameId,
                                  @PathVariable("countryId") Integer countryId) {
        cardsService.cardsInGameAdding(playerId, gameId, countryId);
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
            result.setcCode(resultSet.getInt("c_code"));

            return result;
        }
    }
}













