package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.plants.demo.entity.Image;
import project.plants.demo.service.ImageService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/show")
    public String showImage(Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "show_image";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/image/show?error";
        }

        try {
            imageService.saveImage(file);  // 이미지를 저장하고, 데이터베이스에 경로도 저장
        } catch (IOException e) {
            // Error handling
            return "redirect:/image/show?error";
        }

        return "redirect:/image/show";
    }
}
