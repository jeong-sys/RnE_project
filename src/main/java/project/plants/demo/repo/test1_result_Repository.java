package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.plants.demo.entity.test1_result_Entity;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface test1_result_Repository extends JpaRepository<test1_result_Entity, Long> {

    @Query("SELECT text FROM test1_result_Entity ORDER BY id DESC")
    List<String> find2Text(Pageable pageable);

}
