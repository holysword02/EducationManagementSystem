package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Survey;
import com.ems.question.mapper.SurveyMapper;
import com.ems.question.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl extends ServiceImpl<SurveyMapper, Survey> implements ISurveyService {
    
}
