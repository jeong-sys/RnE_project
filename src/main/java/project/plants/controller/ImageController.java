package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@Controller
@EnableCaching
public class ImageController {

    @Autowired
    private ImageFinishService imageFinishService;

    @Autowired
    private ImageService imageService;

    private String send_condition;

    @GetMapping("/getImages")
    @ResponseBody
    public List<String> getImages(@RequestParam int page, @RequestParam String condition) { // 페이지, 조건을 파라미터로 받음
        ArrayList<String> encodedImageList = new ArrayList<>(); // Base64(binary Data를 텍스트로 변경)로 인코딩된 이미지 저장 리스트

        System.out.println(condition);
        send_condition = condition;
        return imageService.getImages(page, condition); }

    // condition 유효하지 않으면 예외 발생
//        if (!isValidTable(condition)) {
//            throw new IllegalArgumentException("Invalid table name"); // throw : 고의로 예외 발생
//        }

    // 테이블명 유효한지 확인하는 메서드(SQL Injection 방지)
//    private boolean isValidTable(String tableName) {
//        List<String> validTables = List.of("test1", "test2"); // 유효한 테이블 목록
//        return validTables.contains(tableName); // 테이블명 유효한지 확인 후 반환
//    }

    @GetMapping("/select_condition")
    public String showImagePage() {
        return "show_images";
    }

    @PostMapping("/api/saveCacheToDB")
    public ResponseEntity<String> saveCacheFromClient(@RequestBody List<CacheItem> cacheItems) {
        System.out.println("Received data : " + cacheItems);
        for (CacheItem item : cacheItems) {
            imageFinishService.saveCache(item, send_condition);
        }
        return ResponseEntity.ok("Cache saved successfully");
    }
}