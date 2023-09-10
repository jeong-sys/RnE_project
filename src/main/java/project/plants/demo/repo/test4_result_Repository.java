package project.plants.demo.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.plants.demo.entity.test4_result_Entity;

import java.util.List;

public interface test4_result_Repository extends JpaRepository<test4_result_Entity, Long> {

    @Query("SELECT text FROM test4_result_Entity ORDER BY id DESC")
    List<String> find2Text(Pageable pageable);

}
