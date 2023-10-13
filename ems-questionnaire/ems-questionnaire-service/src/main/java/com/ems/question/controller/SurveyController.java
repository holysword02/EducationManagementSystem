package com.ems.question.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.po.Survey;
import com.ems.question.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final ISurveyService surveyService;

    //分页查询
    @GetMapping("/find")
    public IPage<Survey> find(Integer pageNum, Integer pageSize) {
        IPage<Survey> ip = new Page<>(pageNum, pageSize);
        return surveyService.page(ip);
    }

    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Survey survey) {
        return surveyService.save(survey);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Survey survey) {
        return surveyService.updateById(survey);
    }

    //删除
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return surveyService.removeById(id);
    }

}
