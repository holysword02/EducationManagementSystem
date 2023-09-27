package com.ems.question.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Question;
import com.ems.question.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/subject")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    //分页查询
    @GetMapping("/find")
    public IPage<Question> find(Integer pageNum, Integer pageSize) {
        IPage<Question> ip = new Page<>(pageNum, pageSize);
        return questionService.page(ip);
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Question question) {
        return questionService.save(question);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Question question) {
        return questionService.updateById(question);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return questionService.removeById(id);
    }

}
