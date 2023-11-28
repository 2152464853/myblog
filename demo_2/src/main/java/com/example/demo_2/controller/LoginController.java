package com.example.demo_2.controller;

import com.example.demo_2.dao.UserDao;
import com.example.demo_2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;


@RestController
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public HashMap<String,Object> userlogin(@RequestBody User user){
        String flag="error";
        HashMap<String, Object> res=new HashMap<>();
        System.out.println(user);
        User us=userDao.getUserByMassage(user.getUsername(),user.getPassword());
        System.out.println(us);
        if(us!=null) flag="ok";
        res.put("flag",flag);
        res.put("user",us);
        //String res_json=JSON.toJSONString(res);
        return res;
    }
}
