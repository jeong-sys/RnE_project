package project.plants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.plants.demo.repo.PlantRepository;
import project.plants.service.PlantService;

@Configuration
public class PlantConfig {
    private final PlantRepository plantRepository;

    public PlantConfig(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Bean
    public PlantService plantService() {
        return new PlantService(this.plantRepository);
    }
}
