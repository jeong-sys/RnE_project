package project.plants.demo.entity;

import javax.persistence.*;

@Entity//DB 테이블에 저장될 클래스
@Table (name = "test_total")
public class test1_Entity {

    @Id // ID , DB에서는 PK(Primary Key)라고 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 관리, 생성할 때마다 1,2,.. 증가
    private Long id;

    @Column (name = "filename")
    private String filename;

    @Column (name = "filepath")
    private String filepath;

    @Column (name = "condition_id")
    private String condition_id;

}



