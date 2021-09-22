package com.geoleo.kaardid.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository


public class CardsRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public boolean uniqueName(String name) {
        String sql = "SELECT player_name = :name FROM players";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return jdbcTemplate.queryForObject(sql, paramMap, boolean.class);


    }

//    public String insertName(String name) {
//        String sql = "INSERT INTO players (player_name, points, wins, game_id, move) " +
//                "VALUES (:name, :points, :wins, :move) ";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", name);
//        paramMap.put("points", 0);
//        paramMap.put("wins", 0);
//        paramMap.put("move", true);
//        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
//
//    }



}
