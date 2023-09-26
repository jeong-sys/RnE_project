package project.plants.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NameController {
    @GetMapping("/start_button")
    public String writeName() {return "name";}
}
