package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Setter
@Entity//DB 테이블에 저장될 클래스
public class PlantDataEntity {

    @Id
    private long id;

    @Column(length = 255)
    private String date;

    @Column(length = 255)
    private String type;

    @Column(length = 255)
    private String imgPath;

    // 생성자, getters, setters 등 필요한 메서드 추가
    public String getImgPath() {
        return imgPath;
    }

    public String geType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}