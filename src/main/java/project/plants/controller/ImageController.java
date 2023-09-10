package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.plants.demo.service.ImageFinishService;
import project.plants.demo.service.ImageService;

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
        send_condition = condition;
        return imageService.getImages(page, condition);
    }

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