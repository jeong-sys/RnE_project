package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.EnableCaching;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.plants.demo.repo.ImageRepository;
import project.plants.demo.entity.Image;


import java.awt.*;
import java.io.IOException;

import java.util.List;


@Controller
@EnableCaching
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/select_condition")
    public String showImages(Model model) {
        List<java.awt.Image> images = imageRepository.findAll();
        model.addAttribute("images", images);
        return "show_images";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Image img = new Image();
        img.setData(file.getBytes());
        imageRepository.save(img);
        return "redirect:/images/show";
    }



}

