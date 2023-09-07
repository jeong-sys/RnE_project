package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.plants.demo.repo.PlantRepository;

import javax.persistence.Cacheable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Controller
@EnableCaching
public class ImageController {

    @Autowired
    private ImageService imageService;

    private String send_condition;

    @PostMapping("/api/saveCacheToDB")
    public ResponseEntity<String> saveCacheFromClient(@RequestBody List<CacheItem> cacheItems) {
        for (CacheItem item : cacheItems) {
            imageService.saveCache(item, send_condition);
        }
        return ResponseEntity.ok("Cache saved successfully");
    }


    // 테이블명 유효한지 확인하는 메서드(SQL Injection 방지)
    private boolean isValidTable(String tableName) {
        List<String> validTables = List.of("test1", "test2"); // 유효한 테이블 목록
        return validTables.contains(tableName); // 테이블명 유효한지 확인 후 반환
    }

    @GetMapping("/getImages")
    @ResponseBody
    public List<String> getImages(@RequestParam int page, @RequestParam String condition) { // 페이지, 조건을 파라미터로 받음

        send_condition = condition;

        // condition 유효하지 않으면 예외 발생
        if (!isValidTable(condition)) {
            throw new IllegalArgumentException("Invalid table name"); // throw : 고의로 예외 발생
        }

        return imageService.getImages(page, condition);
    }

    @GetMapping("/select_condition")
    public String showImagePage() {
        return "show_images";
    }
}

