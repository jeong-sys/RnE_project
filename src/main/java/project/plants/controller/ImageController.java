package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String showImage(Model model, @RequestParam(defaultValue = "0") int page) {
        PageRequest pageable = PageRequest.of(page, 5, Sort.by("id").ascending());
        Page<Image> imagesPage = imageService.getAllImages(pageable);

        model.addAttribute("images", imagesPage.getContent());
        model.addAttribute("totalPages", imagesPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "show_image";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("alertMessage", "파일을 선택해 주세요.");
            return "redirect:/image/show?error";
        }

        try {
            imageService.saveImage(file);  // 이미지를 저장하고, 데이터베이스에 경로도 저장
        } catch (IllegalStateException e) {
            model.addAttribute("alertMessage", e.getMessage());
            Pageable pageable = PageRequest.of(0, 5, Sort.by("id").ascending());
            model.addAttribute("images", imageService.getAllImages(pageable).getContent());
            return "show_image";
        } catch (IOException e) {
            model.addAttribute("alertMessage", "이미지 저장 중 오류가 발생했습니다.");
            return "redirect:/image/show?error";
        }

        return "redirect:/image/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return "redirect:/image/show";
    }

}
