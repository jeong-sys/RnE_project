package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.plants.demo.dto.ImageDTO;
import project.plants.demo.entity.Image;
import project.plants.demo.repo.ImageRepository;

import javax.transaction.Transactional;
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

    private static final String UPLOAD_DIR = "src/main/resources/static/img/learning/"; // 이미지를 저장할 경로 지정

    public Image saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        // DB에서 모든 이미지를 조회
        List<Image> existingImages = imageRepository.findAll();

//        if (!existingImages.isEmpty()) {
//            throw new IllegalStateException("이미 이미지가 업로드 되어 있습니다.");
//        }

        // 새 이미지 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Image newImage = new Image();
        newImage.setFileName(fileName);
        newImage.setPath("/img/learning/" + fileName);

        return imageRepository.save(newImage);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid image Id:" + id));

        // 파일 시스템에서 이미지 삭제 // 전체 삭제
        File file = new File(UPLOAD_DIR + image.getFileName());
        if(file.exists()) {
            file.delete();
        }
        // DB에서 이미지 정보 삭제
        imageRepository.deleteById(id);
    }

    /* Paging */
    @Transactional
    public Page<Image> pageList(Pageable pageable) {
        return imageRepository.findAll(pageable); // 'imageRepository' 사용
    }
    
}
