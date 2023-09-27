package com.ems.subject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Subject;
import com.ems.subject.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final ISubjectService subjectService;

    //分页查询
    @GetMapping("/find")
    public IPage<Subject> find(Integer pageNum, Integer pageSize) {
        IPage<Subject> ip = new Page<>(pageNum, pageSize);
        return subjectService.page(ip);
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Subject subject) {
        return subjectService.save(subject);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Subject subject) {
        return subjectService.updateById(subject);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return subjectService.removeById(id);
    }

}
