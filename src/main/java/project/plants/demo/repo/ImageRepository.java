package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.plants.demo.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
