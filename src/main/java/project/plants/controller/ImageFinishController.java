package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.plants.demo.service.ImageFinishService;

import java.util.List;
import java.util.Map;

@Controller
public class ImageFinishController {

    @Autowired
    private ImageFinishService imageFinishService;

    @GetMapping("/getCondition")
    @ResponseBody
    public String getCondition() {
        return imageFinishService.send_DB_condition();
    }

    @GetMapping("/getTexts")
    public ResponseEntity<Map<String, List<String>>> getTexts(){

        String condition_result = imageFinishService.send_DB_condition();
        Map<String, List<String>> map = imageFinishService.displayText(condition_result); // text를 day에 따라 분류하기 위한 hashmap
        return ResponseEntity.ok(map);

    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}
