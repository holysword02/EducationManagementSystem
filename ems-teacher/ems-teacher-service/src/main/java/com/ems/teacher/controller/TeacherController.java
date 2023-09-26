package com.ems.teacher.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Teacher;
import com.ems.teacher.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping("/find")
    public IPage<Teacher> find(Integer pageNum, Integer pageSize) {
        IPage<Teacher> ip = new Page<>(pageNum, pageSize);
        return teacherService.page(ip);
    }

    //根据id查询教师信息
    @GetMapping("/getTeacherById")
    public String getTeacherById(Integer id) {
        return teacherService.getById(id).toString();
    }

    //修改教师信息
    @PutMapping("/update")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    //新增教师信息
    @PostMapping("/add")
    public boolean addTeacher(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    //删除教师信息
    @DeleteMapping("/delete/{id}")
    public boolean deleteTeacher(@PathVariable Serializable id) {
        return teacherService.removeById(id);
    }
}
