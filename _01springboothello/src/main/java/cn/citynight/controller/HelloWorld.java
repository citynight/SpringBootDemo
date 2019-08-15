package cn.citynight.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class HelloWorld {

    @RequestMapping(path = {"/hello"})
    public Map<String,Object> showHelloWorld() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","HelloWorld");
        return map;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
