package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import project.plants.demo.entity.PlantDataEntity;
import project.plants.demo.entity.PlantEntity;
import project.plants.demo.repo.PlantDataRepository;
import project.plants.demo.repo.PlantRepository;


@Controller
public class ImageGetController {

    @Autowired
    private PlantDataRepository plantdatarepository;

    @Autowired
    private PlantRepository plantrepository;

    @GetMapping("/getImage")
    public String showImages(Model model) {
        PlantEntity args = plantrepository.findById(1L).orElse(null);
        PlantDataEntity data = plantdatarepository.findById(1L).orElse(null);

        model.addAttribute("waterOImgPath", data.getImgPath());
        model.addAttribute("argsImgPath", args.getArgs());

        return "displayImage";
    }


    // view 로 출력
    @GetMapping("/showImagePage")
    public String showImagePage() {
        return "displayImage";
    }

}

