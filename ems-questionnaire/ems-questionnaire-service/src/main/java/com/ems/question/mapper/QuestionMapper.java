package com.ems.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.api.domain.po.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {


}