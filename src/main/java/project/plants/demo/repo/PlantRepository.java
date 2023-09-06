package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.plants.demo.entity.PlantEntity;

// 인터페이스 생성
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {

}