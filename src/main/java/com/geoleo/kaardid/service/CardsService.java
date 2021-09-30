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


    public UUID checkGame(Integer playerID, Boolean gameType) {
        try {
            UUID gameid = cardsRepository.checkEmptyPlayer2();
            cardsRepository.setGameReady(gameid);
            cardsRepository.setPlayer2(playerID, gameid);
            return gameid;
        } catch (EmptyResultDataAccessException e) {
            return cardsRepository.createGame(playerID, gameType);
        }
    }


    public UUID createGame(Integer firstPlayerID, Boolean gameType) {
        return cardsRepository.createGame(firstPlayerID, gameType);
    }

    public String joinGame(Integer playerId, UUID gameId) {
        if (cardsRepository.checkEmptyPlayerJoin(gameId)) {
            cardsRepository.setPlayer2(playerId, gameId);
            cardsRepository.setGameReady(gameId);
            return "Mänguga ühinetud";
        } else {
            return "Mäng on täis";
        }

    }

    public boolean checkReady(UUID gameId) {

        return cardsRepository.checkReady(gameId);
    }

    public boolean checkNameId(String name, Integer playerId) {

        return cardsRepository.checkNameId(name, playerId);
    }

    public Boolean checkWhoIsFirst(UUID gameId, Integer playerId) {
        try {
            Integer whois = cardsRepository.checkWhoIsFirst(gameId, playerId);
            Integer move = cardsRepository.checkMove(gameId);

            if (whois >= 1 && move == 1) {
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            Integer move = cardsRepository.checkMove(gameId);
            if (move == 2) {
                return true;
            } else {
                return false;

            }


        }

    }

    public void randomCardsInGame(UUID gameId) {
        cardsRepository.randomCardsInGame(gameId);

        for (int i = 1; i < 6; i++) {
            cardsRepository.randomCardsCount(gameId, i);
        }

    }


    public int choose1card(UUID gameId, int cardCount) {
        return cardsRepository.choose1card(gameId, cardCount);
    }

    public Boolean checkIfInputYes(UUID gameId, Integer cardCount) {
        try {
            cardsRepository.checkIfInputYes(gameId, cardCount);
            return true;



        } catch (EmptyResultDataAccessException e) {
            return false;



        }


    }
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







