package com.geoleo.kaardid.repository;


import com.geoleo.kaardid.controller.PollResponse;
import com.geoleo.kaardid.service.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository


public class CardsRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private UUID gameId;

    public Integer uniqueName(String name) {
        String sql = "SELECT count (*) FROM players WHERE player_name = :name";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);


    }

    public Integer insertName(String name) {
        String sql = "INSERT INTO players (player_name) VALUES (:name)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("players_id");


    }


//    public UUID checkEmptyPlayer1() {
//        String sql = "SELECT games_id FROM games WHERE player1_id IS NULL ORDER BY random() * 1 LIMIT 1";
//        Map<String, Object> paramMap = new HashMap<>();
//        return jdbcTemplate.queryForObject(sql, paramMap, UUID.class);
//
//    }

    public UUID checkEmptyPlayer2() {
        String sql = "SELECT games_id FROM games WHERE player2_id IS NULL AND game_type IS false ORDER BY random() * 1 LIMIT 1";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, UUID.class);
    }

    public Boolean checkEmptyPlayerJoin(UUID gameId) {
        String sql = "SELECT player2_id FROM games WHERE games_id= :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        Integer player2 = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        if (player2 == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setPlayer2(int playerid, UUID gameid) {
        String sql = "UPDATE games SET player2_id = :plID WHERE games_id = :gmid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gmid", gameid);
        paramMap.put("plID", playerid);
        jdbcTemplate.update(sql, paramMap);

    }


    public void setGameTypetrue(UUID gameid) {
        String sql = "UPDATE games SET buddy_game = true WHERE games_id = :gameid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameid", gameid);
        jdbcTemplate.update(sql, paramMap);

    }

    public boolean checkReady(UUID gameid) {
        String sql = "SELECT ready FROM games WHERE games_id = :gameid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameid", gameid);
        return jdbcTemplate.queryForObject(sql, paramMap, boolean.class);

    }

    public void setGameReady(UUID gameid) {
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
        String sql = "INSERT INTO games (player1_id, game_type, move) VALUES (:playerId, :isGameType, :randomMove)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("playerId", firstPlayerID);
        paramMap.put("isGameType", gameType);
        paramMap.put("randomMove", randomMove);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        UUID games_id = (UUID) keyHolder.getKeys().get("games_id");
        return games_id;
    }

    public boolean checkNameId(String name, int playerId) {
        String sql = "SELECT players_id FROM players WHERE player_name = :name";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        int checkId = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        if (checkId == playerId) {
            return true;
        } else {
            return false;
        }
    }

    public Integer checkWhoIsFirst(UUID gameId, Integer playerId) {
        String sql = "SELECT player1_id FROM games WHERE games_id = :gameId and player1_id = :playerId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("playerId", playerId);
        paramMap.put("gameId", gameId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);

    }


    public Integer checkMove(UUID gameId) {
        String sql = "SELECT move FROM games WHERE  games_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);

    }


    public void writepolling(UUID gameId, Integer nr) {
        String sql = "UPDATE games SET checkpolling = :nr WHERE games_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        paramMap.put("nr", nr);
        jdbcTemplate.update(sql, paramMap);

    }

    public void randomCardsInGame(UUID gameId) {

        String sqlinsert = "INSERT INTO cardsingame (country_id) SELECT countries_id FROM countries ORDER BY random()";
        Map<String, Object> paramMapInsert = new HashMap<>();
        jdbcTemplate.update(sqlinsert, paramMapInsert);

        String sqlinsertgameid = "UPDATE cardsingame SET game_id = :gameId WHERE card_count IS NULL";
        Map<String, Object> paramMapInsertGameId = new HashMap<>();
        paramMapInsertGameId.put("gameId", gameId);
        jdbcTemplate.update(sqlinsertgameid, paramMapInsertGameId);
    }

    public void randomCardsCount(UUID gameId, Integer i) {

        String sqlselect = "SELECT cardsingame_id FROM cardsingame WHERE game_id = :gameId AND card_count IS NULL LIMIT 1";
        Map<String, Object> paramMapSelect = new HashMap<>();
        paramMapSelect.put("gameId", gameId);
        Integer randomId;
        randomId = jdbcTemplate.queryForObject(sqlselect, paramMapSelect, Integer.class);

        String sqlcount = "UPDATE cardsingame SET card_count = :i WHERE cardsingame_id = :thisId";
        Map<String, Object> paramMapCount = new HashMap<>();
        paramMapCount.put("i", i);
        paramMapCount.put("thisId", randomId);
        jdbcTemplate.update(sqlcount, paramMapCount);

    }

    public int choose1card(UUID gameId, int cardCount) {
        String sql = "SELECT country_id FROM cardsingame WHERE card_count = :cardCount AND game_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        paramMap.put("cardCount", cardCount);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }


    public boolean doMoreCardsExist(UUID gameId, int cardCount) {
        String sql = "SELECT count(*) FROM cardsingame WHERE card_count =:cardCount AND game_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        paramMap.put("cardCount", cardCount);
        int count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
        return count > 0;
    }


    public PollResponse checkIfInputYes(UUID gameId, Integer cardCount) {
        String sql = "SELECT value_chosen, player_id  FROM cardsingame WHERE game_id = :gameId AND card_count = :cardCount";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        paramMap.put("cardCount", cardCount);
        return jdbcTemplate.queryForObject(sql, paramMap, new ResponseRowMapper());
    }

    private class ResponseRowMapper implements RowMapper<PollResponse> {
        @Override
        public PollResponse mapRow(ResultSet resultSet, int i) throws SQLException {
            PollResponse response = new PollResponse();
            response.setChosenField(resultSet.getString("value_chosen"));
            response.setWinner(resultSet.getInt("player_id"));

            return response;
        }
    }


    public Country getCountry(int countryId) {
        String sql = "SELECT * FROM countries WHERE countries_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", countryId);
        return jdbcTemplate.queryForObject(sql, paramMap, new CountryRowMapper());
    }

    public int retrieveCountry(UUID gameId, Integer cardcount) {
        String sql = "SELECT country_id FROM cardsingame WHERE game_id = :gameId AND card_count = :cardCount";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        paramMap.put("cardCount", cardcount);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public int retrieve2ID(UUID gameId) {
        String sql = "SELECT player2_id FROM games WHERE games_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public int retrieve1ID(UUID gameId) {
        String sql = "SELECT player1_id FROM games WHERE games_id = :gameId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gameId", gameId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void writeWinner(Integer playerId, UUID gameId, Integer cardcount, String chosenField) {
        String sqlcount = "UPDATE cardsingame SET player_id = :playerId WHERE game_id = :gameId AND card_count = :cardcount";
        Map<String, Object> paramMapCount = new HashMap<>();
        paramMapCount.put("playerId", playerId);
        paramMapCount.put("gameId", gameId);
        paramMapCount.put("cardcount", cardcount);
        jdbcTemplate.update(sqlcount, paramMapCount);

        String sqloldpoints = "SELECT points FROM players WHERE players_id = :playerId";
        Map<String, Object> paramMapOld = new HashMap<>();
        paramMapOld.put("playerId", playerId);
        try {
            int oldPoints = jdbcTemplate.queryForObject(sqloldpoints, paramMapOld, Integer.class);
            int newPoints = oldPoints + 1;

            String sqlpoints = "UPDATE players SET points = :newPoints  WHERE players_id= :playerId";
            Map<String, Object> paramMapPoints = new HashMap<>();
            paramMapPoints.put("playerId", playerId);
            paramMapPoints.put("newPoints", newPoints);
            jdbcTemplate.update(sqlpoints, paramMapPoints);
        } catch (NullPointerException e) {
            int oldPoints = 0;
            int newPoints = oldPoints + 1;

            String sqlpoints = "UPDATE players SET points = :newPoints  WHERE players_id= :playerId";
            Map<String, Object> paramMapPoints = new HashMap<>();
            paramMapPoints.put("playerId", playerId);
            paramMapPoints.put("newPoints", newPoints);
            jdbcTemplate.update(sqlpoints, paramMapPoints);
        }

    }

    private class CountryRowMapper implements RowMapper<Country> {
        @Override
        public Country mapRow(ResultSet resultSet, int i) throws SQLException {
            Country result = new Country();
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
            result.setCountryCode(resultSet.getString("country_code"));
            result.setHighestName(resultSet.getString("highest_name"));
            result.setLowestName(resultSet.getString("lowest_name"));
            result.setFlagColors(resultSet.getInt("flag_colors"));
            result.setContourCode(resultSet.getString("contour_code"));
            return result;
        }
    }

    public void sendChosenField(String chosenField, Integer playerId, UUID gameId, Integer cardcount) {

        String sql = "UPDATE cardsingame SET value_chosen = :chosenField WHERE game_id = :gameId AND card_count= :cardcount";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("chosenField", chosenField);
        paramMap.put("gameId", gameId);
        paramMap.put("cardcount", cardcount);
        jdbcTemplate.update(sql, paramMap);

    }


    public String retrieveSField(int countryId, String chosenField) {
        String sql = "SELECT " + chosenField + " FROM countries WHERE countries_id = :countryId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("chosenField", chosenField);
        paramMap.put("countryId", countryId);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public Double retrieveDField(int countryId, String chosenField) {
        String sql = "SELECT " + chosenField + " FROM countries WHERE countries_id = :countryId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("chosenField", chosenField);
        paramMap.put("countryId", countryId);
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);
    }

    public Integer retrieveIField(int countryId, String chosenField) {

        String sql = "SELECT " + chosenField + " FROM countries WHERE countries_id = :countryId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("countryId", countryId);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void changeMove(UUID gameId, int move) {
        if (move == 1) {
            int newmove = 2;
            String sql = "UPDATE games SET move = :move WHERE games_id = :gameId";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("move", newmove);
            paramMap.put("gameId", gameId);
            jdbcTemplate.update(sql, paramMap);
        } else {
            int newmove = 1;
            String sql = "UPDATE games SET move = :move WHERE games_id = :gameId";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("move", newmove);
            paramMap.put("gameId", gameId);
            jdbcTemplate.update(sql, paramMap);
        }

    }


}


