package project.plants.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "test1")
public class ImageEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "filename", length = 50)
    private String fileName;

    @Column(name = "filepath", length = 1000)
    private String filePath;

    @Column(name = "environment_id")
    private int environmentID;

    @Column(name = "days_id")
    private int daysID;
}



