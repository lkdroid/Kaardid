package com.geoleo.kaardid.service;

import com.geoleo.kaardid.controller.CardDataResponse;
import com.geoleo.kaardid.controller.PollResponse;
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
            UUID gameId = cardsRepository.checkEmptyPlayer2();
            cardsRepository.setGameReady(gameId);
            cardsRepository.setPlayer2(playerID, gameId);
            cardsRepository.randomCardsInGame(gameId);
            Thread.sleep(1000);
            for (int i = 1; i < 7; i++) {
                cardsRepository.randomCardsCount(gameId, i);
            }
            return gameId;
        } catch (EmptyResultDataAccessException e) {
            return cardsRepository.createGame(playerID, gameType);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("viga", e);
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


    public CardDataResponse choose1card(UUID gameId, int cardCount) {
        CardDataResponse cardDataResponse = new CardDataResponse();

        int card1Id = cardsRepository.choose1card(gameId, cardCount);
        Country card1Data = cardsRepository.getCountry(card1Id);
        int card2Id = cardsRepository.choose1card(gameId, cardCount + 1);
        Country card2Data = cardsRepository.getCountry(card2Id);
        cardDataResponse.setCard1(card1Data);
        cardDataResponse.setCard2(card2Data);
        return cardDataResponse;
    }

    public PollResponse checkIfInputYes(UUID gameId, Integer cardCount) {

        try {
            PollResponse pollResponse = cardsRepository.checkIfInputYes(gameId, cardCount);
            if (pollResponse.getWinner() > 0) {
                pollResponse.setResult(true);
            } else {
                pollResponse.setResult(false);
            }
            return pollResponse;
        } catch (NullPointerException e) {
            PollResponse pollResponse = cardsRepository.checkIfInputYes(gameId, cardCount);
            pollResponse.setResult(false);
            return pollResponse;
        }
    }

    public PollResponse sendChosenField(String chosenField, Integer playerId, UUID gameId, Integer cardcount) {
        int country1 = cardsRepository.retrieveCountry(gameId, cardcount);
        int country2 = cardsRepository.retrieveCountry(gameId, cardcount + 1);
        if (cardsRepository.checkMove(gameId) == 1) {
            if (chosenField == "country_name" || chosenField == "capital") {
                int field1 = (cardsRepository.retrieveSField(country1, chosenField)).length();
                int field2 = (cardsRepository.retrieveSField(country2, chosenField)).length();
                if (field1 > field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);

                } else {

                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }
            } else if (chosenField == "hdi" || chosenField == "water" || chosenField == "density") {
                double field1 = cardsRepository.retrieveDField(country1, chosenField);
                double field2 = cardsRepository.retrieveDField(country2, chosenField);
                if (field1 > field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);
                } else {
                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }
            } else {
                int field1 = cardsRepository.retrieveIField(country1, chosenField);
                int field2 = cardsRepository.retrieveIField(country2, chosenField);
                if (field1 > field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);
                } else {
                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }

            }
        } else { //siit algab teine monster IFIFIFIFIF
            if (chosenField == "country_name" || chosenField == "capital") {
                int field1 = (cardsRepository.retrieveSField(country1, chosenField)).length();
                int field2 = (cardsRepository.retrieveSField(country2, chosenField)).length();
                if (field1 < field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);

                } else {

                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }
            } else if (chosenField == "hdi" || chosenField == "water" || chosenField == "density") {
                double field1 = cardsRepository.retrieveDField(country1, chosenField);
                double field2 = cardsRepository.retrieveDField(country2, chosenField);
                if (field1 < field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);
                } else {
                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }
            } else {
                int field1 = cardsRepository.retrieveIField(country1, chosenField);
                int field2 = cardsRepository.retrieveIField(country2, chosenField);
                if (field1 < field2) {
                    Integer player1Id = cardsRepository.retrieve1ID(gameId);
                    cardsRepository.writeWinner(player1Id, gameId, cardcount, chosenField);
                } else {
                    Integer player2Id = cardsRepository.retrieve2ID(gameId);
                    cardsRepository.writeWinner(player2Id, gameId, cardcount, chosenField);
                }


            }
        }
        cardsRepository.sendChosenField(chosenField, playerId, gameId, cardcount);
        return cardsRepository.checkIfInputYes(gameId, cardcount);
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







