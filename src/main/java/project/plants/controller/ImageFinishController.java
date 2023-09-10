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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getTexts")
    public ResponseEntity<Map<String, String>> getTexts(){

        String condition_result = imageFinishService.send_DB_condition(); // table명 가져옴
        ArrayList<String> textList = new ArrayList<>(); // DB에서 가져온 text 담는 배열
        Map<String, String> map = new HashMap<>(); // text를 day에 따라 분류하기 위한 hashmap

        // 내림차순 정렬 후 필요한 text만큼 가져옴(가장 최근의 7개 데이터)
        String sql = "SELECT text FROM " + condition_result + " ORDER BY id DESC LIMIT 2";

        try {
            jdbcTemplate.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    try {
                        String text = rs.getString("text");
                        textList.add(text);
                    } catch (Exception e) {
                        throw new SQLException("Failed to read the file.", e);
                    }
                    return null;
                }
            });

        }catch(Exception e){
            System.out.println("SQL 문이 틀렸습니다");
            e.printStackTrace();
        }

        // text_List에서 day에 따라 String으로 구분
        String text_day1 = textList.get(1);
        String text_day2 = textList.get(0);

        // String보고 키워드 있으면 {key:day, value:keyword}저장
        // table name : test1_result
        if ("test1_result".equals(condition_result)) {

            // test1_day1
            if (text_day1.contains("day1_1")) {
                map.put("day1", "day1_1");
            }
            if (text_day1.contains("day1_2")) {
                map.put("day1", "day1_2");
            }
            // test1_day2
            if (text_day2.contains("day2_1")) {
                map.put("day2", "day2_1");
            }
        }
        // table name : test2_result
        if ("test2_result".equals(condition_result)) {

            // test2_day1
            if (text_day1.contains("day1_1")) {
                map.put("day1", "day1_1");
            }
            // test2_day2
            if (text_day2.contains("day2_1")) {
                map.put("day2", "day2_1");
            }
        }

        return ResponseEntity.ok(map);
    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}
