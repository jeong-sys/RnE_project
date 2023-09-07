package project.plants.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.plants.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("SELECT i.filePath FROM ImageEntity i WHERE i.id BETWEEN :startID AND :startID * 2")
    List<String> findFilePathById(@Param("startID") Long startId);
}