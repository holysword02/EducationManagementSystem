package com.ems.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.User;
import com.ems.system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    // 新增用户
    @PostMapping("/insert")
    public boolean insert(@RequestBody User user) {
        return userService.insert(user);
    }

    // 删除用户
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id, @RequestHeader("Cookie") String cookie) {
        return userService.del(id, cookie);
    }

    // 修改用户
    @PutMapping("/update")
    public boolean updatePassWd(@RequestBody User user, @RequestHeader("Cookie") String cookie) {
        return userService.updateUser(user, cookie);
    }

    // 分页查询
    @GetMapping("/find")
    public IPage<User> find(Integer pageNum, Integer pageSize) {
        IPage<User> ip = new Page<>(pageNum, pageSize);
        return userService.page(ip);
    }

    // 查询 教师
    @GetMapping("/list/teacher")
    public List<User> findByTeacher() {
        QueryWrapper<User> qw = new QueryWrapper<User>()
                .eq("role", 1)
                .eq("is_active", 1);
        return userService.list(qw);
    }

    // 查询 学生
    @GetMapping("/list/student")
    public List<User> findByStudent() {
        QueryWrapper<User> qw = new QueryWrapper<User>()
                .eq("role", 2)
                .eq("is_active", 1);
        return userService.list(qw);
    }
}
