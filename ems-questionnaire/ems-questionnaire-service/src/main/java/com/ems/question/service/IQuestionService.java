package com.ems.question.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Question;
import common.treenode.TreeNode;

import java.util.List;

public interface IQuestionService extends IService<Question> {

    public List<TreeNode> find();


    List<Tree<String>> constructTree(List<Question> dataList);
}
