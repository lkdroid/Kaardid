package com.geoleo.kaardid.repository;

import com.geoleo.kaardid.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void usersave(String kasnimi, String parool) {
        String sql = "INSERT INTO passwords (kasnimi,parool) VALUES (:kasnimi, :parool) ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("kasnimi", kasnimi);
        paramMap.put("parool", parool);
        jdbcTemplate.update(sql, paramMap);
    }
    public String checkPassword(String kasnimi) {
        String sql = "SELECT parool FROM passwords WHERE kasnimi = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", kasnimi);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }
}
