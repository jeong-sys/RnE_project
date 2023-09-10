package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import project.plants.demo.dto.test1Form;
import project.plants.demo.dto.test2Form;
import project.plants.demo.entity.test1_result_Entity;
import project.plants.demo.entity.test2_result_Entity;
import project.plants.demo.repo.test1_result_Repository;
import project.plants.demo.repo.test2_result_Repository;

import java.util.*;

@Service
public class ImageFinishService {

    private String DB_condition;
    private final test1_result_Repository test1ResultRepository;
    private final test2_result_Repository test2ResultRepository;

    @Autowired
    public ImageFinishService(@Qualifier("test1_result_Repository") test1_result_Repository test1ResultRepository,
                              @Qualifier("test2_result_Repository") test2_result_Repository test2ResultRepository) {
        this.test1ResultRepository = test1ResultRepository;
        this.test2ResultRepository = test2ResultRepository;
    }

    public void saveCache(CacheItem item, String condition) {

        String key = item.getKey();
        String page = key.split("-")[2];
        Integer pageValue = Integer.parseInt(page);
        String value = item.getValue();

        System.out.println("@@@@@@@@@@");
        System.out.println(condition);

        if ("test1".equals(condition)) {
            // condition 에 맞는 테이블로 저장
            test1Form test1form = new test1Form();
            test1form.setPage(pageValue);
            test1form.setText(value);
            test1_result_Entity test1ResultEntity = test1form.toEntity();
            test1ResultRepository.save(test1ResultEntity);
        }
        else if ("test2".equals(condition)) {
            test2Form test2form = new test2Form();
            test2form.setPage(pageValue);
            test2form.setText(value);
            test2_result_Entity test2ResultEntity = test2form.toEntity();
            test2ResultRepository.save(test2ResultEntity);
        }

        DB_condition = condition + "_result";
    }

    public String send_DB_condition(){
        return DB_condition;
    }

    public Map<String, String> displayText(String condition_result) {
        Map<String, String> map = new HashMap<>();
        List<String> textList = new ArrayList<>();

        if ("test1_result".equals(condition_result)) {
            // test1_result 테이블에 대한 처리
            textList = test1ResultRepository.fileTexts(PageRequest.of(0,1));

            //LIMIT 말고 다른거 써야함

            Collections.reverse(textList);

            for(String text : textList) {
                if (text.contains("day1_1")) {
                    map.put("day1", "day1_1");
                }
                if (text.contains("day1_2")) {
                    map.put("day1", "day1_2");
                }
                if (text.contains("day2_1")) {
                    map.put("day2", "day2_1");
                }
            }
        } else if ("text2_result".equals(condition_result)) {
            textList = test2ResultRepository.fileTexts();
            Collections.reverse(textList);

            for(String text : textList) {
                if (text.contains("day1_1")) {
                    map.put("day1", "day1_1");
                }
                if (text.contains("day2_1")) {
                    map.put("day2", "day2_1");
                }
            }
        }
        return map;
    }
}