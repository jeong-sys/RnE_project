package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageFinishController {

    @Autowired
    private ImageFinishService imageFinishService;

    @GetMapping("/getTexts")
    public ResponseEntity<Map<String, String>> getTexts(){

        String condition_result = imageFinishService.send_DB_condition(); // table명 가져옴
        Map<String, String> map = imageFinishService.displayText(condition_result); // text를 day에 따라 분류하기 위한 hashmap

        return ResponseEntity.ok(map);
    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}
