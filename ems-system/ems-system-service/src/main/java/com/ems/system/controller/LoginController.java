package com.ems.system.controller;

import com.ems.system.entity.User;
import com.ems.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String token = loginService.login(user);
        if (token != null) {
            return "登陆成功";
        } else {
            return "用户名或密码错误";
        }
    }
    @GetMapping("/get/{token}")
    public User getUserInfoByToken(@PathVariable String token) {
        return loginService.getUserInfoByToken(token);
    }
}
