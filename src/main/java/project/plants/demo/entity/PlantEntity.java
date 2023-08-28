package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Setter
@Entity//DB 테이블에 저장될 클래스
public class PlantEntity {

    @Id // ID , DB에서는 PK(Primary Key)라고 함
    private Long id;

    @Column(length = 255)
    private String args;
}