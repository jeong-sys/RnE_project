package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.plants.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class NameController {
    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public String name() {
        return "name";
    }

    @PostMapping("/name")
    public String saveName(@RequestParam String nickname) {
        userService.findOrCreateByNickname(nickname);
        return "List";
    }
}

