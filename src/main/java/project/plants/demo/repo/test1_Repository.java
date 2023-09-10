package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.plants.demo.entity.test1_Entity;

import java.util.List;

// 인터페이스 생성
@Repository
public interface test1_Repository extends JpaRepository<test1_Entity, Long> {

    // PlantRepository : DB와 소통하는 인터페이스, JPA가 해당 객체를 알아서 만듦
    // <PlantEntity : 관리 대상, Long : 타입>
    @Query("SELECT i.filepath FROM test1_Entity i WHERE i.id BETWEEN :startID AND :startID * 2")
    List<String> findFilePathById(@Param("startID") Long startId);

}