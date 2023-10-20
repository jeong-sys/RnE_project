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
import project.plants.demo.dto.ImageDTO;
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
//        for(int i = 0; i < images.size(); i++)
//            System.out.println(images.get(i).getFileName());
        System.out.println(images);
        model.addAttribute("images", images);
        return "show_image";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile[] files, Model model) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // Skip empty files
            }

            try {
                imageService.saveImage(file);
            } catch (IllegalStateException | IOException e) {
                model.addAttribute("alertMessage", "Error uploading image: " + e.getMessage());
                model.addAttribute("images", imageService.getAllImages());
                return "show_image";
            }
        }

        return "redirect:/image/show";
    }


    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return "redirect:/image/show";
    }

}
