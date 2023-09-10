package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.plants.demo.entity.test2_result_Entity;

import java.util.List;

public interface test2_result_Repository extends JpaRepository<test2_result_Entity, Long> {

    @Query("SELECT text FROM test2_result_Entity ORDER BY id DESC")
    List<String> fileTexts();
}
