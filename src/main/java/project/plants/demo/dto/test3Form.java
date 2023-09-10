package project.plants.demo.dto;


import lombok.Data;
import project.plants.demo.entity.test3_result_Entity;

@Data
public class test3Form {
    private Integer page;
    private String text;

    public test3_result_Entity toEntity(){
        return test3_result_Entity.builder()
                .id(null)
                .page(page)
                .text(text)
                .build();
    }
}
