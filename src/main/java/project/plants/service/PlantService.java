package project.plants.service;

import project.plants.demo.repo.PlantRepository;

public class PlantService {
    
    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }
}
