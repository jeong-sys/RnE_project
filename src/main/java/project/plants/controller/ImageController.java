package project.plants.controller;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.plants.service.ImageFinishService;
import project.plants.service.ImageService;

import java.util.*;

@Controller
@EnableCaching
public class ImageController {

    private ImageService imageService;
    private ImageFinishService imageFinishService;

    public ImageController(ImageService imageService, ImageFinishService imageFinishService) {
        this.imageService = imageService;
        this.imageFinishService = imageFinishService;
    }


    private String send_condition;
    @GetMapping("/getImages")
    @ResponseBody
    public List<String> getImages(@RequestParam int page, @RequestParam String condition) {
        send_condition = condition;
        return imageService.getImages(page, condition);
    }

    @GetMapping("/select_condition")
    public String showImagePage() {
        return "show_images";
    }

    @PostMapping("/saveCacheToDB")
    public ResponseEntity<String> saveCacheFromClient(@RequestBody List<CacheItem> cacheItems) {
        for (CacheItem item : cacheItems) {
            imageFinishService.saveCache(item, send_condition);
        }
        return ResponseEntity.ok("Cache saved successfully");
    }
}