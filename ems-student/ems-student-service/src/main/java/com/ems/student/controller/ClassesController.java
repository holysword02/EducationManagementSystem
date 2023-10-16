package com.ems.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Classes;
import com.ems.student.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassesController {

    private final IClassesService classesService;

    //分页查询
    @GetMapping("/find")
    public IPage<Classes> find(Integer pageNum, Integer pageSize) {
        IPage<Classes> ip = new Page<>(pageNum, pageSize);
        return classesService.page(ip);
    }

    //查询全部
    @GetMapping("/findAll")
    public List<Classes> findAll() {
        return classesService.list();
    }

    //根据id查询
    @GetMapping("/getById")
    public Classes getById(Long id) {
        return classesService.getById(id);
    }

    //根据id批量查询
    @PostMapping("/getByIds")
    public List<Classes> getByIds(@RequestBody List<Long> ids) {
        return classesService.selectByIds(ids);
    }

    //根据name批量查询
    @PostMapping("/getByNames")
    public List<Classes> getByNames(@RequestBody List<String> names) {
        return classesService.selectByNames(names);
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Classes classes) {
        return classesService.save(classes);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Classes classes) {
        return classesService.updateById(classes);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return classesService.removeById(id);
    }


}
