package cn.citynight.controller;

import cn.citynight.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/*
*
* SpringBoot整合jsp
* */

@Controller
public class UserController {
    /*处理请求，产生数据*/
    @RequestMapping("/showUser")
    public String showUser(Model model) {

        List<Users> list = new ArrayList<>();
        list.add(new Users(1,"zhangsan",20));
        list.add(new Users(2,"lisi",22));
        list.add(new Users(3,"wangwu",24));

        // 把数据传递到jsp中需要一个model对象
        model.addAttribute("list",list);
        // 跳转视图
System.out.println("show User List ....");
        return  "userList";
    }
}
