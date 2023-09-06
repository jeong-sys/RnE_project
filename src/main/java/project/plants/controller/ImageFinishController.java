package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ImageFinishController {

    @Autowired
    private ImageFinishService imageFinishService;
    // DB에 있는 text 가져오기
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getTexts")
    public List<String> getTexts(){
        ArrayList<String> textList = new ArrayList<>();

        String condition_result = imageFinishService.send_DB_condition();
        System.out.println("condition_result : "+ condition_result);
        
        String sql = "SELECT text FROM " + condition_result + " ORDER BY id DESC LIMIT 2";
        
        try {
            jdbcTemplate.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {

                    try {
                        String text = rs.getString("text");
                        System.out.println("text : " + text);
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
        System.out.println("textList" + textList);

        HashMap<String, String> map = new HashMap<>();

        // 선택한 조건에서의 text List
        String text_day1 = textList.get(1);
        System.out.println("day1 text : " + text_day1);
        String text_day2 = textList.get(0);
        System.out.println("day2 text : " + text_day2);

        System.out.println(condition_result);

        if ("test1_result".equals(condition_result)) {

            System.out.println("@@@ test1_result @@@");
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

        if ("test2_result".equals(condition_result)) {

            System.out.println("@@@ test2_result @@@");
            // test2_day1
            if (text_day1.contains("day1_1")) {
                map.put("day1", "day1_1");
            }
            // test2_day2
            if (text_day2.contains("day2_1")) {
                map.put("day2", "day2_1");
            }
        }

        System.out.println(map);

        return (List<String>) map;
    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}

