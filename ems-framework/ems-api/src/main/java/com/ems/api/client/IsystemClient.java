package com.ems.api.client;

import com.ems.api.domain.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ems-system-service")
public interface IsystemClient {
    @PostMapping("/user/insert")
    Boolean createUser(@RequestBody User user);



}
