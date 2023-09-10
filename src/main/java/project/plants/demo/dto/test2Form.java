package project.plants.demo.dto;

import lombok.Data;
import project.plants.demo.entity.test2_result_Entity;

@Data
public class test2Form {

    private Integer page;
    private String text;

    public test2_result_Entity toEntity(){
        return test2_result_Entity.builder()
                .id(null)
                .page(page)
                .text(text)
                .build();
    }
}
