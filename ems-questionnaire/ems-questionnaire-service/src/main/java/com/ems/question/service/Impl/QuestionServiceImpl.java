package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Question;
import com.ems.question.mapper.QuestionMapper;
import com.ems.question.service.IQuestionService;
import common.treenode.InfiniteTree;
import common.treenode.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    private final QuestionMapper questionMapper;

    @Override
    public List<TreeNode> find() {
        InfiniteTree<QuestionMapper, Question> tree = new InfiniteTree<>(questionMapper);
        QueryWrapper<Question> qw = new QueryWrapper<>();
        qw.eq("parent_id", 0L);
        List<Question> list = list(qw);
        return tree.getTreeNodeList(list, 3, "parent_id", "id", "id", "label");
    }
}
