package com.ems.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Classes;
import com.ems.student.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassesController {

    private final IClassesService teacherService;

    //分页查询
    @GetMapping("/find")
    public IPage<Classes> find(Integer pageNum, Integer pageSize) {
        IPage<Classes> ip = new Page<>(pageNum, pageSize);
        return teacherService.page(ip);
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Classes classes) {
        return teacherService.save(classes);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Classes classes) {
        return teacherService.updateById(classes);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return teacherService.removeById(id);
    }


}
