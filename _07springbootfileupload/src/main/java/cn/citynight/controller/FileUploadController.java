package cn.citynight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
*
* SpringBoot 文件上传
*
* */
//@Controller
@RestController // 表示该类下的方法的返回值会自动做json格式的转换
public class FileUploadController {
    /*
    * filename的名字要和HTML中的filename一致
    * RequestMapping中的path跟HTML中的action一致
    * */
    @RequestMapping("/fileUploadController")
    public Map<String,Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println(filename.getOriginalFilename());
        File file = new File("/Users/lixiaozheng/Desktop/UploadData/" + filename.getOriginalFilename());
        filename.transferTo(file);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","ok");
        return map;

    }
}
