package project.plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CancelController {

    @GetMapping("/cancel")
    public String cancel(){
        System.out.println("view clink");
        return "Cancel";
    }
}
