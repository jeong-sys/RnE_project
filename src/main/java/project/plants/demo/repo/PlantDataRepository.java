package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.plants.demo.entity.PlantDataEntity;

// 인터페이스 생성
@Repository
public interface PlantDataRepository extends JpaRepository<PlantDataEntity, Long> {
    @Query("SELECT pd.imgPath FROM PlantData pd WHERE pd.date = :date AND pd.type = :type")
    String findImgPathByDateAndType(@Param("date") String date, @Param("type") String type);
}
