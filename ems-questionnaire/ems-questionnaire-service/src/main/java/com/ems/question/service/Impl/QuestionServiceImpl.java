package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Question;
import com.ems.question.mapper.QuestionMapper;
import com.ems.question.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Override
    public IPage<Question> find(Integer pageNum, Integer pageSize) {
        IPage<Question> ip = new Page<>(pageNum, pageSize);
        QueryWrapper<Question> qw = new QueryWrapper<Question>()
                .eq("parent_id", 0L);
        IPage<Question> page = page(ip, qw);
        for (Question q : page.getRecords()) {
            QueryWrapper<Question> qwNode = new QueryWrapper<Question>()
                    .eq("parent_id", q.getId());
            q.setChildren(list(qwNode));
        }
        return page;
    }
}
