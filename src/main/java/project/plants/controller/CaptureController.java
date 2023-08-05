package project.plants.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import project.plants.PlantsApplication;
import project.plants.User;
import project.plants.demo.entity.PlantEntity;
import project.plants.demo.repo.PlantRepository;

import javax.persistence.Cacheable;
import java.util.Optional;

@Controller
public class CaptureController {

    @Autowired // repository 객체 가져옴
    private PlantRepository plantRepository;

    // capture에서 db 정보 조회하기, capture페이지를 안보여줄 수 있나?
    // cookie로 저장


    @RequestMapping(value = "/capture", method = RequestMethod.POST)
    public ModelAndView user(){

        User user = new User();
        ModelAndView modelAndView;

        modelAndView = new ModelAndView("capture");
        modelAndView.addObject("user", user);

        System.out.println("Btn_1 click"); //zmq로 신호 보내
        return modelAndView;
    }

    @RequestMapping(value="/check", method = RequestMethod.POST)
    public String conditionCheck(@ModelAttribute("user")User user, ModelMap model){ // modelmap : 데이터 저장

        // condition 값 조회 : Optional - null이 올 수 있는 값을 감싸는 Wrapper 클래스
        Optional<PlantEntity> plantEntityOpt = plantRepository.findBypcondition(user.getCondition());
        if(plantEntityOpt.isPresent()){
            model.addAttribute("pcondition", user.getCondition());
            return "check";
        }

        else{
            if(user.getCondition() == null){
                user.setCondition("default value");
                return "capture";
            }
            model.addAttribute("pcondition", user.getCondition());

            PlantEntity plantEntity = PlantEntity.builder().id(null).pcondition(user.getCondition()).build();
            plantRepository.save(plantEntity);

            return "check";
        }
    }
}
