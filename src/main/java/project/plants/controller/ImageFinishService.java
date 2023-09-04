package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class ImageFinishService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCache(CacheItem item) {

        String key = item.getKey();
        System.out.println(key);
        String value = item.getValue();
        System.out.println(value);
        String page = key.split("-")[2];

        String sql = "INSERT INTO write_test (page, text) VALUES (?, ?)";
        jdbcTemplate.update(sql, page, value);
    }
}