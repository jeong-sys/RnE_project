package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
