package project.plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import project.plants.demo.repo.PlantRepository;

@Controller
public class HomeController {

    private final PlantRepository pr;

    public HomeController(PlantRepository pr) {
        this.pr = pr;
    }

    @GetMapping("/")
    public String home(){
        System.out.println(this.pr);
        return "Home";
    }

}
