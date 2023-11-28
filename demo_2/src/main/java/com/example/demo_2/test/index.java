package com.example.demo_2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class index {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "ok";
    }
}
