package project.plants.demo.dto;


import lombok.Data;
import project.plants.demo.entity.test4_result_Entity;

@Data
public class test4Form {

    private Integer page;
    private String text;

    public test4_result_Entity toEntity(){
        return test4_result_Entity.builder()
                .id(null)
                .page(page)
                .text(text)
                .build();
    }
}
