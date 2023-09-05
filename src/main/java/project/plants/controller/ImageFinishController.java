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
import java.util.List;

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

        ArrayList<String> rightList = new ArrayList<>();

        String text_day1 = textList.get(1);
        String text_day2 = textList.get(0);


        // 여러개인 경우 어떻게 넣어야할지 생각하기
        if (text_day1.contains("test1")) {
            rightList.add("test1");
        } else { rightList.add(""); }

        if (text_day2.contains("test2")) {
            rightList.add("test2");
        } else { rightList.add(""); }

        System.out.println("rightList" + rightList);

        return rightList;
    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}

