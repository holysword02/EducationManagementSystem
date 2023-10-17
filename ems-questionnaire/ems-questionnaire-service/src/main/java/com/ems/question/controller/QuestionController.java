package com.ems.question.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ems.api.domain.po.Question;
import com.ems.question.service.IQuestionService;

import common.treenode.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    //分页查询
    @GetMapping("/find")
    public List<Tree<String>> getTree() {
        return questionService.constructTree();
    }



    @GetMapping("/options")
    public List<Question> questionList() {
        QueryWrapper<Question> qw = new QueryWrapper<Question>()
                .eq("parent_id", 0L)
                .eq("is_active", 1);
        return questionService.list(qw);
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
