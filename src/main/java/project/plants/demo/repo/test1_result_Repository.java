package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.plants.demo.entity.test1_result_Entity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@Repository
public interface test1_result_Repository extends JpaRepository<test1_result_Entity, Long> {

    @Query("SELECT text FROM test1_result_Entity ORDER BY id DESC")
    List<String> fileTexts(Pageable pageable);

}
