package project.plants.demo.dto;

import lombok.Data;
import project.plants.demo.entity.test1_result_Entity;

@Data
public class test1Form {

    private Integer page;
    private String text;

    public test1_result_Entity toEntity(){
        return test1_result_Entity.builder()
                .id(null)
                .page(page)
                .text(text)
                .build();
    }
}
