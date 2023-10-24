package com.ems.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.SurveyMysqlDTO;
import com.ems.api.domain.po.Survey;
import com.ems.api.domain.po.SurveyMysql;
import com.ems.api.domain.vo.SurveyVO;

import java.util.List;

public interface ISurveyService extends IService<SurveyMysql> {


    List<Survey> getAllSurveys();


    boolean deleteSurvey(String id);

    boolean updateSurvey(Survey survey);

    List<SurveyMysqlDTO> convertRecords(List<SurveyMysql> subjects);

    Survey getSurvey(String id);

    boolean newSurvey(SurveyVO surveyvo);

}