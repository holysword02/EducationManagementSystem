package com.ems.teacher.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.TeacherDTO;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.vo.TeacherIdNameVO;
import com.ems.api.domain.vo.TeacherVO;
import com.ems.teacher.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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

    //查询所有id和name
    @GetMapping("/findAll")
    public List<TeacherIdNameVO> findAll() {
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        qw.select("id", "username", "name"); // 选择 id、username 和 name 字段
        List<Teacher> teachers = teacherService.list(qw);

        // 使用 stream 和 map 方法将 Teacher 列表转换为 TeacherIdNameVO 列表
        return teachers.stream()
                .map(teacher -> new TeacherIdNameVO(teacher.getId(), teacher.getUsername(), teacher.getName()))
                .collect(Collectors.toList()); // 收集到 List 中
    }

    //根据username
    @GetMapping("/getByUsername")
    public Teacher getByUsername(String username) {
        return teacherService.selectByUsername(username);
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
