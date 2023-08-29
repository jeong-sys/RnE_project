package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageFinishController {

    @Autowired
    private ImageFinishService imageFinishService;

    @GetMapping("/ImageFinish")
    public String imageFinish(){
        imageFinishService.saveCache();
        return "finishPage";
    }
}
