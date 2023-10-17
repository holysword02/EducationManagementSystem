package com.ems.question.service.Impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
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
        qw.eq("parent_id",0L);
        List<Question> list = list(qw);
        return tree.getTreeNodeList(list,3,"parent_id","id","id","label");

    }


    @Override
    public List<Tree<String>> constructTree(List<Question> dataList) {

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
// 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
// 最大递归深度
        treeNodeConfig.setDeep(3);

//转换器
        List<Tree<String>> treeNodes = TreeUtil.build(dataList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(String.valueOf(treeNode.getId()));
                    tree.setParentId(String.valueOf(treeNode.getParentId()));
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getLabel());
                    // 扩展属性 ...
                });
        return treeNodes;
    }
}
