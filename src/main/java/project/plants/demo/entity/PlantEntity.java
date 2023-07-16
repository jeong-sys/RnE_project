package project.plants.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter //Getter 메소드 생성
@Builder // 빌더 사용
// @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 디폴트 생성자 넣어줌
@Entity(name="plants") // DB 테이블에 저장될 클래스
public class PlantEntity {

    @Id // ID , DB에서는 PK(Primary Key)라고 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 관리, 생성할 떄마다 1,2,.. 증가
    private Long id;

    @Column(nullable = false, unique = true, length = 30) // length : 최대 글자 수
    private String pcondition;

    @Builder // builder 패턴
    public PlantEntity(Long id, String pcondition){
        this.id = id;
        this.pcondition = pcondition;
    }


}
