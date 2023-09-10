package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import project.plants.controller.CacheItem;
import project.plants.demo.dto.test1Form;
import project.plants.demo.dto.test2Form;
import project.plants.demo.dto.test3Form;
import project.plants.demo.dto.test4Form;
import project.plants.demo.entity.test1_result_Entity;
import project.plants.demo.entity.test2_result_Entity;
import project.plants.demo.entity.test3_result_Entity;
import project.plants.demo.entity.test4_result_Entity;
import project.plants.demo.repo.test1_result_Repository;
import project.plants.demo.repo.test2_result_Repository;

import org.springframework.data.domain.Pageable;
import project.plants.demo.repo.test3_result_Repository;
import project.plants.demo.repo.test4_result_Repository;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class ImageFinishService {

    private String DB_condition;
    private final test1_result_Repository test1ResultRepository;
    private final test2_result_Repository test2ResultRepository;
    private final test3_result_Repository test3ResultRepository;
    private final test4_result_Repository test4ResultRepository;

    @Autowired
    public ImageFinishService(@Qualifier("test1_result_Repository") test1_result_Repository test1ResultRepository,
                              @Qualifier("test2_result_Repository") test2_result_Repository test2ResultRepository,
                              @Qualifier("test3_result_Repository") test3_result_Repository test3ResultRepository,
                              @Qualifier("test4_result_Repository") test4_result_Repository test4ResultRepository) {
        this.test1ResultRepository = test1ResultRepository;
        this.test2ResultRepository = test2ResultRepository;
        this.test3ResultRepository = test3ResultRepository;
        this.test4ResultRepository = test4ResultRepository;
    }

    public void saveCache(CacheItem item, String condition) {

        DB_condition = condition + "_result";

        String key = item.getKey();
        String page = key.split("-")[2];
        Integer pageValue = Integer.parseInt(page);
        String value = item.getValue();

        if ("test1".equals(condition)) {
            // condition 에 맞는 테이블로 저장
            test1Form test1form = new test1Form();
            test1form.setPage(pageValue);
            test1form.setText(value);
            test1_result_Entity test1ResultEntity = test1form.toEntity();
            test1ResultRepository.save(test1ResultEntity);
        }
        if ("test2".equals(condition)) {
            test2Form test2form = new test2Form();
            test2form.setPage(pageValue);
            test2form.setText(value);
            test2_result_Entity test2ResultEntity = test2form.toEntity();
            test2ResultRepository.save(test2ResultEntity);
        }
        if ("test3".equals(condition)) {
            test3Form test3form = new test3Form();
            test3form.setPage(pageValue);
            test3form.setText(value);
            test3_result_Entity test3ResultEntity = test3form.toEntity();
            test3ResultRepository.save(test3ResultEntity);
        }
        if ("test4".equals(condition)) {
            test4Form test4form = new test4Form();
            test4form.setPage(pageValue);
            test4form.setText(value);
            test4_result_Entity test4ResultEntity = test4form.toEntity();
            test4ResultRepository.save(test4ResultEntity);
        }
    }

    public String send_DB_condition(){
        return DB_condition;
    }

    public Map<String, List<String>> displayText(String condition_result) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> textList;


        if ("test1_result".equals(condition_result)) {
            // test1_result 테이블에 대한 처리

            textList = test1ResultRepository.find2Text(PageRequest.of(0, 7));
            Collections.reverse(textList);

            if (textList.get(0).contains("day1_1")) {
                map.computeIfAbsent("day1", k -> new ArrayList<>()).add("day1_1");
            }
            if (textList.get(0).contains("day1_2")) {
                map.computeIfAbsent("day1", k -> new ArrayList<>()).add("day1_2");
            }
            if (textList.get(1).contains("day2_1")) {
                map.computeIfAbsent("day2", k -> new ArrayList<>()).add("day2_1");
            }

        }

        if ("test2_result".equals(condition_result)) {

            textList = test2ResultRepository.find2Text(PageRequest.of(0, 7));
            Collections.reverse(textList);

            if (textList.get(0).contains("day1_1")) {
                map.computeIfAbsent("day1", k -> new ArrayList<>()).add("day1_1");
            }
            if (textList.get(1).contains("day2_1")) {
                map.computeIfAbsent("day2", k -> new ArrayList<>()).add("day2_1");
            }

        }

        if ("test3_result".equals(condition_result)) {

            textList = test3ResultRepository.find2Text(PageRequest.of(0, 7));
            Collections.reverse(textList);

            if (textList.get(0).contains("안녕")) {
                map.computeIfAbsent("day1", k -> new ArrayList<>()).add("day1_1");
            }
            if (textList.get(1).contains("day2_1")) {
                map.computeIfAbsent("day2", k -> new ArrayList<>()).add("day2_1");
            }

        }

        if ("test4_result".equals(condition_result)) {

            textList = test4ResultRepository.find2Text(PageRequest.of(0, 7));
            Collections.reverse(textList);

            if (textList.get(0).contains("잘가")) {
                map.computeIfAbsent("day1", k -> new ArrayList<>()).add("day1_1");
            }
            if (textList.get(1).contains("day2_1")) {
                map.computeIfAbsent("day2", k -> new ArrayList<>()).add("day2_1");
            }

        }
        return map;
    }
}