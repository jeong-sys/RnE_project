package project.plants.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinalController {

    @GetMapping("/final_page")
    public String final_page(){
        return "final";
    }
}
