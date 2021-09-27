package com.geoleo.kaardid.service;

import com.geoleo.kaardid.repository.CardsRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service



public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;


    public Boolean uniqueName(String name) {
        if (cardsRepository.uniqueName(name) == 0) {
            return true;
        } else {
            return false;
        }

    }


    public Integer insertName(String name) {
        return cardsRepository.insertName(name);

    }

//    public int checkGame(String name, Integer playerid) {
//
//    }

//    public int checkGame(String name, Integer playerid) {
//        return cardsRepository.checkGame();
//
//    }

//    public Integer createGame() {
//        return cardsRepository.createGame();
//
//    }
}

