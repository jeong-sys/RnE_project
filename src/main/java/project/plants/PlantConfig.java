package project.plants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import project.plants.repo.ImageRepository;
import project.plants.service.ImageService;

@Configuration
public class PlantConfig {
    private final ImageRepository plantRepository;

    public PlantConfig(ImageRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Bean
    public ImageService plantService() {
        return new ImageService(this.plantRepository);
    }
}
