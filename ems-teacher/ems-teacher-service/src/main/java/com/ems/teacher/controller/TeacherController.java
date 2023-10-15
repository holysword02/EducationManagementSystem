package com.ems.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.TeacherDTO;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.vo.StudentVO;
import com.ems.api.domain.vo.TeacherVO;
import com.ems.teacher.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping("/find")
    public TeacherVO find(Integer pageNum, Integer pageSize) {
        IPage<Teacher> ip = new Page<>(pageNum, pageSize);
        List<TeacherDTO> teacherDTOS = teacherService.convertTeachers(teacherService.page(ip).getRecords());
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setRecords(teacherDTOS);
        teacherVO.setTotal(ip.getTotal());
        teacherVO.setSize(ip.getSize());
        teacherVO.setCurrent(ip.getCurrent());
        return teacherVO;
    }



    //新增教师信息和用户信息
    @PostMapping("/addTeacherAndUser")
    public String addTeacherAndUser(@RequestBody Teacher teacher) {
        teacherService.addTeacherAndUser(teacher);
        return "success";
    }

    //根据id批量查询
    @PostMapping("/getByIds")
    public List<Teacher> getByIds(@RequestBody List<Long> ids) {
        return teacherService.selectByIds(ids);
    }

    //根据name批量查询
    @PostMapping("/getByNames")
    public List<Teacher> getByNames(@RequestBody List<String> names) {
        return teacherService.selectByNames(names);
    }


    //根据id
    @GetMapping("/getById")
    public Teacher getById(Long id) {
        return teacherService.getById(id);
    }

    //修改教师信息
    @PutMapping("/update")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateById(teacher);
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
