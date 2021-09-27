package com.geoleo.kaardid.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository


public class CardsRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Integer uniqueName(String name) {
        String sql = "SELECT count (*) FROM players WHERE player_name = :name";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);


    }

    public Integer insertName(String name) {
        String sql = "INSERT INTO players (player_name) VALUES (:name) ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("players_id");


    }


    public UUID checkEmptyPlayer1() {
        String sql = "SELECT games_id FROM games WHERE player1_id IS NULL ORDER BY random() * 1 LIMIT 1";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, UUID.class);

    }

    public UUID checkEmptyPlayer2() {
        String sql = "SELECT games_id FROM games WHERE player2_id IS NULL ORDER BY random() * 1 LIMIT 1";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, UUID.class);


    }

    public void setBuddyGametrue(UUID gameid) {
        String sql = "UPDATE games SET buddy_game = true WHERE games_id = :gameid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameid", gameid);
        jdbcTemplate.update(sql, paramMap);

    }

    public void setgameReady(UUID gameid) {
        String sql = "UPDATE games SET ready = true WHERE games_id = :gameid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameid", gameid);
        jdbcTemplate.update(sql, paramMap);

    }

    public void setMove(Integer playerid) {
        String sql = "UPDATE players SET move = true WHERE players_id = :playerid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("playerid", playerid);
        jdbcTemplate.update(sql, paramMap);

    }



    public UUID createGame(Integer firstPlayerID, Boolean gameType) {
        int randomMove = (Math.random() <= 0.5) ? 1 : 2;
        String sql = "INSERT INTO games (player1_id, buddy_game, move) VALUES (:name, :isBuddyGame, :randomMove)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", firstPlayerID);
        paramMap.put("isBuddyGame", gameType);
        paramMap.put("randomMove", randomMove);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        UUID games_id = (UUID) keyHolder.getKeys().get("games_id");
        return games_id;
    }











}












// kdjfg