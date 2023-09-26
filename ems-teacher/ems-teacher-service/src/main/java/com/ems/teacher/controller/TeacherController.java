package com.ems.teacher.controller;

import com.ems.api.domain.po.Teacher;
import com.ems.teacher.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    @Autowired
    private final ITeacherService teacherService;

    //根据id查询教师信息
    @GetMapping("/getTeacherById")
    public String getTeacherById(Integer id){
        return teacherService.getById(id).toString();
    }

    //修改教师信息
    @PutMapping("/update")
    public void updateTeacher(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }

    //新增教师信息
    @PostMapping("/add")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
    }

    //删除教师信息
    @DeleteMapping("/delete")
    public void deleteTeacher(Integer id) {
        teacherService.removeById(id);
    }
}
