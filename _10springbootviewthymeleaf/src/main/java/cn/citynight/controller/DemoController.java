package cn.citynight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*thymeleaf 入门案例*/
@Controller
public class DemoController {
    @RequestMapping("/show")
    public String showInfo(Model model) {
        model.addAttribute("msg","thymeleaf 第一个案例");
        return "index";
    }
}
