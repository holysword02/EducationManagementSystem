package com.ems.system.controller;

import com.ems.api.domain.po.User;
import com.ems.system.service.ILoginService;
import common.result.TokenData;
import common.result.UserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private final ILoginService ILoginService;

    @PostMapping("/login")
    public UserResult login(@RequestBody User user) {
        TokenData tokenData = ILoginService.login(user);
        if (tokenData != null) {
            return UserResult.success(tokenData);
        } else {
            return UserResult.fail();
        }
    }

    @GetMapping("/get/{token}")
    public User getUserInfoByToken(@PathVariable String token) {
        return ILoginService.getUserInfoByToken(token);
    }
}
