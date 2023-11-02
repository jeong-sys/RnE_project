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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.plants.demo.dto.ImageDTO;
import project.plants.demo.entity.Image;
import project.plants.demo.entity.x_Image;
import project.plants.demo.service.ImageService;
import project.plants.demo.service.XImageService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private XImageService xImageService;

    @PostMapping("/uploadAndMove")
    public String uploadImageAndMoveToNextPage(@RequestParam(name = "uploadImage", required = false) MultipartFile file,
                                               @RequestParam(name = "currentPage", required = false, defaultValue = "1") int page,
                                               Model model, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                Image image = imageService.saveImage(file, page);  // 페이지 번호를 파라미터로 추가
            } catch (IllegalStateException | IOException e) {
                redirectAttributes.addFlashAttribute("alertMessage", "Error uploading image: " + e.getMessage());
                return "redirect:/image/show?page=" + page;
            }
        } else {
            redirectAttributes.addFlashAttribute("alertMessage", "No image selected for upload.");
            return "redirect:/image/show?page=" + page;
        }

        return "redirect:/image/show?page=" + (page + 1);
    }


    @GetMapping("/show")
    public String showImage(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
        List<Image> images = imageService.getImagesByPage(page);
        x_Image xImage = xImageService.getImageByPage(page);
        model.addAttribute("Ximage", xImage);
        model.addAttribute("currentImage", images.isEmpty() ? null : images.get(0));
        model.addAttribute("currentPage", page); // 현재 페이지 번호를 뷰에 전달
        return "show_image";
    }
}

