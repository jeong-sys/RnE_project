package project.plants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import project.plants.controller.CacheItem;


@Service
public class ImageFinishService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String DB_condition;

    public void saveCache(CacheItem item, String condition) {
        String key = item.getKey();
        String value = item.getValue();
        String page = key.split("-")[2];

        DB_condition = condition + "_result";

        String sql = "INSERT INTO " + DB_condition +" (page, text) VALUES (?, ?)";
        jdbcTemplate.update(sql, page, value);
    }

    public String send_DB_condition(){
        return DB_condition;
    }
}