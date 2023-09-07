package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ImageFinishController {

    @Autowired
    private ImageFinishService imageFinishService;

    @GetMapping("/getTexts")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getTexts(){

        imageFinishService.getTexts();
        return ResponseEntity.ok(imageFinishService.getTexts());

    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}

