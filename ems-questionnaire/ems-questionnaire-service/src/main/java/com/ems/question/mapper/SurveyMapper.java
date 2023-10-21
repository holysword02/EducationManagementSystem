package com.ems.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.api.domain.po.Survey;
import com.ems.api.domain.po.SurveyMysql;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper extends BaseMapper<SurveyMysql> {

}