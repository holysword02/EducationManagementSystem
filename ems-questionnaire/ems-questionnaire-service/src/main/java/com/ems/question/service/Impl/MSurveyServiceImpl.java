package com.ems.question.service.Impl;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ems.api.domain.po.Survey;
import com.ems.question.repository.SurveyRepository;
import com.ems.question.service.MSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSurveyServiceImpl implements MSurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(String id) {
        Survey survey = surveyRepository.findById(id).orElse(null);



//        String value = survey.getValue();
//        value = value.replaceAll("\\\"","u0022");
//        survey.setValue(value);
        return survey;
    }

    @Override
    public boolean deleteSurvey(String id) {
        surveyRepository.deleteById(id);
        return true;
    }
}
