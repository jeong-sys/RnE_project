package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.plants.demo.entity.x_Image;

import java.util.Optional;

public interface XImageRepository extends JpaRepository<x_Image, Long> {
    Optional<x_Image> findByPage(int page);
}
