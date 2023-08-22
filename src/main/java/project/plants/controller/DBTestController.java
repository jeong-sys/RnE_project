package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.plants.demo.entity.PlantEntity;
import project.plants.demo.repo.PlantRepository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


// DB에 있는 이미지 불러오기
@Controller
public class DBTestController {

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping("/showImage")
    public String showImage(Model model) {

        PlantEntity img = plantRepository.findById(1L).orElse(null); // 1번 엔티티 속성값 가져옴



        System.out.println("log @@@@@@@");
        System.out.println(img);
        if (img != null) {
            byte[] imageBytes = img.getFile();
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            model.addAttribute("imageBase64", imageBase64);
        }

        return "check_image";
    }

//    @GetMapping("/showImage")
//    public String showImage(Model model) {
//        List<PlantEntity> plants = plantRepository.findAll();
//        List<String> imageBase64List = new ArrayList<>();
//
//        for (PlantEntity plant : plants) {
//            byte[] imageBytes = plant.getData();
//            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
//            imageBase64List.add(imageBase64);
//        }
//
//        model.addAttribute("imageBase64List", imageBase64List);
//
//        return "check_image";
//    }
}
