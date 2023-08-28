package project.plants.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.plants.demo.entity.PlantEntity;

import java.util.List;

// 인터페이스 생성
@Repository
public interface PlantRepository extends JpaRepository<PlantEntity, Long> {
    //@Query("SELECT a.PlantEntity FROM PlantEntity a")
    List<PlantEntity> findAll();


}