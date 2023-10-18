package com.ems.question.service.Impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Question;
import com.ems.question.mapper.QuestionMapper;
import com.ems.question.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Override
    public List<Tree<String>> constructTree(Integer deep,String parent) {
        List<Question> dataList = list();
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名
        treeNodeConfig.setIdKey("value");
        treeNodeConfig.setNameKey("label");
        treeNodeConfig.setWeightKey("order");
        // 最大递归深度
        treeNodeConfig.setDeep(deep);
        //转换器 (含义:找出父节点为字符串零的所有子节点, 并递归查找对应的子节点, 深度最多为 3)
        return TreeUtil.build(dataList, parent, treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(String.valueOf(treeNode.getId()));
                    tree.setParentId(String.valueOf(treeNode.getParentId()));
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getLabel());
                    // 扩展属性 ...
                });
    }
}
