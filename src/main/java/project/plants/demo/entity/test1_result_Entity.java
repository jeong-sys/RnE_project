package project.plants.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "test1_result")
public class test1_result_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "page")
    private Integer page;

    @Column (name = "text")
    private String text;

    @Builder
    public test1_result_Entity(Long id, Integer page, String text){
        this.id = id;
        this.page = page;
        this.text = text;
    }
}
