package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.plants.demo.entity.x_Image;
import project.plants.demo.repo.XImageRepository;

@Service
public class XImageService {

    @Autowired
    private XImageRepository xImageRepository;

    public x_Image getImageByPage(int page) {
        return xImageRepository.findByPage(page)
                .orElseThrow(() -> new RuntimeException("Image not found for page " + page));
    }
}
