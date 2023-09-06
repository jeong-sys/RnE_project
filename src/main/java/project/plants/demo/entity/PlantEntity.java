package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test2")
public class PlantEntity {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "filename", length = 50)
    private String filename;

    @Column(name = "filepath", length = 1000)
    private String filepath;

    @Column(name = "environment_id")
    private int environment_id;

    @Column(name = "days_id")
    private int days_id;

}



