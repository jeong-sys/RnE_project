package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        
        String sql = "SELECT text FROM" + condition_result;
        
        try {
            jdbcTemplate.query(sql, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {

                    try {
                        String text = rs.getString("text");
                        System.out.println("text : " + text);
                    } catch (Exception e) {
                        throw new SQLException("Failed to read the file.", e);
                    }
                    return null;
                }
            }).forEach(text -> textList.add(text));

        }catch(Exception e){
            System.out.println("SQL 문이 틀렸습니다");
            e.printStackTrace();
        }
        System.out.println("textList" + textList);
        return textList;
    }

    @GetMapping("/score_check")
    public String imageFinish() {
        return "finishPage";
    }
}

