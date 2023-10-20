package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.plants.demo.service.ImageService;

@Controller
public class ListController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/ImageList")
    public String ImageList(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("posts", imageService.pageList(pageable));
        return "List";
    }
}

