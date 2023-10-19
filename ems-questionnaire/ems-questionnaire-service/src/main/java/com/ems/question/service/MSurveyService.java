package com.ems.question.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ems.api.domain.po.Survey;

import java.util.List;

public interface MSurveyService {

    Survey createSurvey(Survey survey);

    List<Survey> getAllSurveys();

    Survey getSurveyById(String id);

    boolean deleteSurvey(String id);
}
