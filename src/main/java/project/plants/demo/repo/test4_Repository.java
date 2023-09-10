package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.plants.demo.entity.test4_Entity;

import java.util.List;

public interface test4_Repository extends JpaRepository<test4_Entity, Long> {

    @Query("SELECT i.filepath FROM test4_Entity i WHERE i.id BETWEEN :startID AND :startID * 2")
    List<String> findFilePathById(@Param("startID") Long startId);
}
