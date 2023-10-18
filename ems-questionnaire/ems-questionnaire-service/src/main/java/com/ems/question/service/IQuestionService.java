package com.ems.question.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Question;

import java.util.List;

public interface IQuestionService extends IService<Question> {
    public List<Tree<String>> constructTree(Integer deep,String parent);
}
