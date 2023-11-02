package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.plants.demo.entity.Image;
import project.plants.demo.repo.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/img/upload/";

    public Image saveImage(MultipartFile file, int page) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalStateException("Uploaded file is empty.");
        }

        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // 새 이미지 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image newImage = new Image();
        newImage.setFileName(fileName);
        newImage.setPath("/img/upload/" + fileName);
        newImage.setPage(page);  // 페이지 번호 설정

        return save(newImage);  // 저장하고 결과를 반환
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    // 페이지에 따른 이미지 가져오기 (페이지 크기는 1로 가정)
    public List<Image> getImagesByPage(int page) {
        Page<Image> imagesPage = imageRepository.findAll(PageRequest.of(page - 1, 1));
        return imagesPage.getContent();
    }
}
