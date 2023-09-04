package project.plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 쿠키 사용 안함
@Controller
public class CookieTestController {

    private String condition = "";

    @GetMapping("/save_capture")
    public String getCapturePage(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if ("selectedOption".equals(cookie.getName())) {
                    model.addAttribute("selected", true);
                    model.addAttribute("value", cookie.getValue());
                    condition = cookie.getValue();
                    // System.out.println(cookie.getValue());
                    return "cookie_condition";
                }
            }
        }
        model.addAttribute("selected", false);
        return "cookie_condition";
    }

    @PostMapping("/save_capture")
    public String captureOption(@RequestParam(required = false) String option, @RequestParam(required = false) String action, HttpServletResponse response, Model model) {

        if ("delete".equals(action)) {
            Cookie cookie = new Cookie("selectedOption", null); // 빈 값을 가진 쿠키 생성
            cookie.setMaxAge(0); // 유효 기간을 0으로 설정하여 쿠키 삭제
            response.addCookie(cookie);
            return "redirect:/save_capture"; // save_capture 다시 불러옴
        }

        // 라디오 버튼 값 저장 로직
        Cookie cookie = new Cookie("selectedOption", option);
        // System.out.println(option);
        cookie.setMaxAge(60 * 60 * 24 * 30); // 유효기간 30일
        response.addCookie(cookie);
        model.addAttribute("selected", true);
        model.addAttribute("value", option);
        return "cookie_condition";
    }

    // 조건 선택 값 ImageGetController에 넘겨줌
    public String condition(){
        return this.condition;
    }
}
