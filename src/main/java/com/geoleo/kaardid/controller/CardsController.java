package com.geoleo.kaardid.controller;

import com.geoleo.kaardid.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        public boolean checkReady (@PathVariable("gameId") UUID gameId){
        return cardsService.checkReady(gameId);
        }
    }













