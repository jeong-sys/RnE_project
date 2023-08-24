package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Setter
@Entity//DB 테이블에 저장될 클래스
public class PlantDataEntity {

    @Id
    private Long id;
    private String date;
    private String type;
    private String imgpath;

    // 생성자, getters, setters 등 필요한 메서드 추가
}