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


    public Integer checkEmptyPlayer1() {
        String sql = "SELECT games_id FROM games WHERE player1_id IS NULL ORDER BY random() * 1 LIMIT 1";
        Map<String, Object> paramMap = new HashMap<>();
         return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);

    }
    public Integer checkEmptyPlayer2() {
        String sql = "SELECT games_id FROM games WHERE player2_id IS NULL ORDER BY random() * 1 LIMIT 1";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);

    }







//    public Integer createGame() {
//        String sql = "INSERT INTO games(games_id) VALUES (random()* 100+1)";
//        Map<String, Object> paramMap = new HashMap<>();
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
//        return (Integer) keyHolder.getKeys().get("games_id");
//
//
//
//
//    }
}
// kdjfg