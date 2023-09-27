package com.ems.question.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Question;

import java.util.List;

public interface IQuestionService extends IService<Question> {

    public IPage<Question> find(Integer pageNum, Integer pageSize);
}
