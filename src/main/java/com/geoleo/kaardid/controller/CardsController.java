package com.geoleo.kaardid.controller;

import com.geoleo.kaardid.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @Autowired
    private CardsService cardsService;


    @GetMapping("uniquename/{name}")
    public Boolean uniqueName(@PathVariable("name") String name) {

        return cardsService.uniqueName(name);

    }

    @PostMapping("insertname/{name}")
    public Integer insertName(@PathVariable("name") String name) {
        return cardsService.insertName(name);

    }

//    @PostMapping("creategame")
//    public Integer createGame() {
//        return cardsService.createGame();
//
//    }


//    @GetMapping("checkgame/{name}/{playerid}")
//    public int checkGame(@PathVariable("name") String name,
//                         @PathVariable("playerid") Integer playerid) {
//        return cardsService.checkGame(name, playerid);
//
//
//    }
}












