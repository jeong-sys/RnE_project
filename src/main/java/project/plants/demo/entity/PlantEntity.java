package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Setter
@NoArgsConstructor
@Entity//DB 테이블에 저장될 클래스
public class PlantEntity {

    @Id // ID , DB에서는 PK(Primary Key)라고 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 관리, 생성할 떄마다 1,2,.. 증가
    private Long id;
    private String filename;

    @Lob
    private byte[] file;

}



