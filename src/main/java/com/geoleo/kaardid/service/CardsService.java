package com.geoleo.kaardid.service;

import com.geoleo.kaardid.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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


//    public UUID checkGame(Integer playerID, Boolean gameType) {
//        try {
//            return cardsRepository.checkEmptyPlayer2();
//        } catch (EmptyResultDataAccessException e) {
//            return cardsRepository.createGame(firstplayerID, gameType);
//        }
//}


        public UUID createGame(Integer firstPlayerID, Boolean gameType) {
            return cardsRepository.createGame(firstPlayerID, gameType);
        }

//    public UUID checkGame(Integer playerid, String playername) {
//        checkEmpty1 = cardsRepository.checkEmptyPlayer1();
//        checkEmpty2 = cardsRepository.checkEmptyPlayer2();
//
//        if (cardsRepository.checkEmptyPlayer1() == null && cardsRepository.checkEmptyPlayer2() == null) {
//            return cardsRepository.createGame;
//        }
//        else if (cardsRepository.checkEmptyPlayer1()) {
//
//
//
//
//        }
    }






