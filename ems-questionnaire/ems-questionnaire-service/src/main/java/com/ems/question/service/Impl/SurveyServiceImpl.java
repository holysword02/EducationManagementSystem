package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IsubjectClient;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.*;
import com.ems.api.domain.dto.SurveyMysqlDTO;
import com.ems.api.domain.vo.SurveyVO;
import com.ems.question.mapper.SurveyMapper;
import com.ems.question.repository.SurveyRepository;
import com.ems.question.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl extends ServiceImpl<SurveyMapper, SurveyMysql> implements ISurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private IsubjectClient subjectClient;

    @Override
    public boolean createSurvey(Survey survey) {
        surveyRepository.save(survey);
        SurveyMysql surveyMysql = new SurveyMysql();
        surveyMysql.setFieldId(survey.getId());
        surveyMysql.setName("test1");
        surveyMapper.insert(surveyMysql);
        return true;
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public SurveyVO getSurveyById(String id) {
        SurveyMysql surveyMysql = surveyMapper.selectById(id);
        Survey survey1 = surveyRepository.findById(surveyMysql.getFieldId()).orElse(null);
        SurveyVO survey = new SurveyVO();
        survey.setId(id);
        survey.setFieldId(surveyMysql.getFieldId());
        if (survey1 != null) {
            survey.setValue(survey1.getValue());
        }
        survey.setName(surveyMysql.getName());
        survey.setCreatDate(surveyMysql.getCreateDate());
        survey.setEndDate(surveyMysql.getEndDate());
        survey.setDescription(surveyMysql.getDescription());
        survey.setIsActive(surveyMysql.getIsActive());

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

    //修改
    @Override
    public boolean updateSurvey(Survey survey) {
        surveyRepository.save(survey);

        return true;
    }

    @Override
    public List<SurveyMysqlDTO> convertRecords(List<SurveyMysql> subjects) {
        //获取所有学科id
        List<Long> subjectNameIds = subjects.stream().map(SurveyMysql::getSubjectId).distinct().collect(Collectors.toList());
        //远程查询学科名称
        List<SubjectDTO> subjectNames = subjectClient.getSubjects(subjectNameIds);
        //将学科id和学科名称对应起来
        Map<Long, SubjectDTO> subjectNameMap = subjectNames.stream().collect(Collectors.toMap(SubjectDTO::getId, Function.identity()));

        return subjects.stream().map(subject -> {
            SurveyMysqlDTO surveyMysqlDTO = new SurveyMysqlDTO();
            surveyMysqlDTO.setId(subject.getId());
            surveyMysqlDTO.setSubjectId(subject.getSubjectId());
            surveyMysqlDTO.setCreateDate(subject.getCreateDate());
            surveyMysqlDTO.setEndDate(subject.getEndDate());
            surveyMysqlDTO.setDescription(subject.getDescription());
            surveyMysqlDTO.setIsActive(subject.getIsActive());
            surveyMysqlDTO.setName(subject.getName());
            surveyMysqlDTO.setFieldId(subject.getFieldId());
            SubjectDTO subjectName = subjectNameMap.get(subject.getSubjectId());
            surveyMysqlDTO.setSubjectName(subjectName != null ? subjectName.getSubjectName() : null);

            return surveyMysqlDTO;
        }).collect(Collectors.toList());
    }
}
