package project.plants.demo.repo;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.plants.demo.entity.test3_Entity;

import java.util.List;

public interface test3_Repository extends JpaRepository<test3_Entity, Long> {

    @Query("SELECT i.filepath FROM test3_Entity i WHERE i.id BETWEEN :startID AND :startID * 2")
    List<String> findFilePathById(@Param("startID") Long startId);
}
