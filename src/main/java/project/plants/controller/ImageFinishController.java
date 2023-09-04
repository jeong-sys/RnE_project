package project.plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ImageFinishController {

    @GetMapping("/ImageFinish")
    public String imageFinish() {
        return "finishPage";
    }
}

