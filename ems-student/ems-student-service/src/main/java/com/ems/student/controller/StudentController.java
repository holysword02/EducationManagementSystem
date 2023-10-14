package com.ems.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Student;
import com.ems.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    //分页查询
    @GetMapping("/find")
    public IPage<Student> find(Integer pageNum, Integer pageSize) {
        IPage<Student> ip = new Page<>(pageNum, pageSize);
        return studentService.page(ip);
    }

    //根据id
    @GetMapping("/getById")
    public Student getById(Long id) {
        return studentService.getById(id);
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


    //分页查询全部学生信息
    @GetMapping("/getAllStudent")
    public List<StudentDTO> getAllStudent(Long pageNum, Long pageSize) {
        return studentService.AllStudent(pageNum, pageSize);
    }

}
