package com.ems.system.controller;

import com.ems.system.entity.User;
import com.ems.system.service.LoginService;
import common.result.TokenData;
import common.result.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public UserResult login(@RequestBody User user) {
        TokenData tokenData = loginService.login(user);
        if (tokenData != null) {
            return UserResult.success(tokenData);
        } else {
            return UserResult.fail();
        }
    }

    @GetMapping("/get/{token}")
    public User getUserInfoByToken(@PathVariable String token) {
        return loginService.getUserInfoByToken(token);
    }
}
