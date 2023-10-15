package com.ems.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.vo.StudentVO;
import com.ems.student.service.IClassesService;
import com.ems.student.service.IStudentService;
import common.exception.BadRequestException;
import common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    private final IClassesService classesService;

    //分页查询
    @GetMapping("/find")
    public List<StudentVO> find(Integer pageNum, Integer pageSize) {
        IPage<Student> ip = new Page<>(pageNum, pageSize);
        return classesService.convertStudents(studentService.page(ip).getRecords());
    }

    //根据id
    @GetMapping("/getById")
    public Student getById(Long id) {
        return studentService.getById(id);
    }

    //根据username
    @GetMapping("/getByUsername")
    public Student getByUsername(String username) {
        return studentService.selectByUsername(username);
    }

    //添加学生和用户
    @PostMapping("/addStudentAndUser")
    public String addStudentAndUser(@RequestBody Student student) {
        studentService.addStudentAndUser(student);
        return "success";
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Student student) {
        return studentService.save(student);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Student student) {
        return studentService.updateById(student);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return studentService.removeById(id);
    }



}
