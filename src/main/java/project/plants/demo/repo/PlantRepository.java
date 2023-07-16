package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.plants.demo.entity.PlantEntity;

import java.util.Optional;

// 인터페이스 생성
@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {

    Optional<PlantEntity> findBypcondition(String pcondition);

    // PlantRepository : DB와 소통하는 인터페이스, JPA가 해당 객체를 알아서 만듦
    // <PlantEntity : 관리 대상, Long : 타입>


}