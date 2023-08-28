package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/showImagePage")
    @ResponseBody
    // For test
    public String showImages(Model model) {
        //PlantEntity args = plantrepository.findById(1L).orElse(null);
        //PlantDataEntity data = plantdatarepository.findById(1L).orElse(null);

        String imgPath = plantdatarepository.findImgPathByDateAndType("day1", "real");

        System.out.println("Log@@@@@" + imgPath);

        model.addAttribute("waterOImgPath", imgPath);
        //model.addAttribute("argsImgPath", args.getArgs());

        return "displayImage";
    }

    // For real System
    // public String showImages(Model model, @RequestParam("date") String date, @RequestParam("type") String type) {
    //     String imgPath = plantdatarepository.findImgPathByDateAndType(date, type);
    //     model.addAttribute("waterOImgPath", imgPath);
    //     return "displayImage";  
    // }


    // // view 로 출력
    // @GetMapping("/showImagePage")
    // public String showImagePage() {
    //     return "displayImage";
    // }

}

