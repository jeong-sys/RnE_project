package project.plants.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import project.plants.demo.repo.ImageRepository;

public class ImageService {
    
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<String> getImages(int page, String condition) {
        ArrayList<String> encodedImages = new ArrayList<>();
        
        Long startIndex = Long.valueOf((page - 1) * 2 + 1);
        imageRepository.findFilePathById(startIndex).forEach(filePath -> {
            try {
                byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
                encodedImages.add(Base64.getEncoder().encodeToString(imageBytes));
            } catch (IOException e) {
            }
        });

        return encodedImages;
    }
}

