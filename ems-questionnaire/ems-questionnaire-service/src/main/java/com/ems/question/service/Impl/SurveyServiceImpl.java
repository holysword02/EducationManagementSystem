package com.ems.question.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IstudentClient;
import com.ems.api.client.IsubjectClient;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.*;
import com.ems.api.domain.dto.SurveyMysqlDTO;
import com.ems.api.domain.vo.SurveyVO;
import com.ems.question.mapper.SurveyMapper;
import com.ems.question.mapper.VoteMapper;
import com.ems.question.repository.SurveyRepository;
import com.ems.question.service.ISurveyService;
import com.ems.question.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private IstudentClient studentClient;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private IVoteService voteService;


    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurvey(String id) {
        return surveyRepository.findById(id).orElse(null);
    }

//    @Override
//    public SurveyVO getSurveyById(String id) {
//        SurveyMysql surveyMysql = surveyMapper.selectById(id);
//        Survey survey1 = surveyRepository.findById(surveyMysql.getFieldId()).orElse(null);
//        SurveyVO survey = new SurveyVO();
//        survey.setId(id);
//        survey.setFieldId(surveyMysql.getFieldId());
//        if (survey1 != null) {
//            survey.setValue(survey1.getValue());
//        }
//        survey.setName(surveyMysql.getName());
//        survey.setCreatDate(surveyMysql.getCreateDate());
//        survey.setEndDate(surveyMysql.getEndDate());
//        survey.setDescription(surveyMysql.getDescription());
//        survey.setIsActive(surveyMysql.getIsActive());
//
////        String value = survey.getValue();
////        value = value.replaceAll("\\\"","u0022");
////        survey.setValue(value);
//        return survey;
//    }


    //新建问卷
    @Override
    public boolean newSurvey(SurveyVO surveyvo) {
        Survey survey = new Survey();
        survey.setValue(surveyvo.getValue());
        surveyRepository.save(survey);
        SurveyMysql surveyMysql = new SurveyMysql();
        surveyMysql.setFieldId(survey.getId());
        surveyMysql.setName(surveyvo.getName());
        surveyMysql.setSubjectId(surveyvo.getSubjectId());
        surveyMysql.setCreateDate(DateUtil.date());
        surveyMysql.setEndDate(surveyvo.getEndDate());
        surveyMysql.setIsActive(1);
        surveyMapper.insert(surveyMysql);
        Subject subject = subjectClient.getSubject(surveyvo.getSubjectId());
        //根据教室id返回所有学生id
        List<Student> students = studentClient.getByClassId(subject.getClassId());
        //批量插入所有学生到vote表
        List<VoteMysql> voteList = new ArrayList<>();
        students.forEach(student -> {
            VoteMysql vote = new VoteMysql();
            vote.setSurveyId(surveyMysql.getId());
            vote.setStudentId(student.getId());
            voteList.add(vote);
        });
        voteService.saveBatch(voteList);
        return true;
    }

    //修改问卷
    @Override
    public boolean updateSurvey(Survey survey) {
        surveyRepository.save(survey);
        return true;
    }

    //删除问卷
    @Override
    public boolean deleteSurvey(String id) {
        SurveyMysql surveyMysql = surveyMapper.selectById(id);
        String fieldId = surveyMysql.getFieldId();
        surveyMapper.deleteById(id);
        surveyRepository.deleteById(fieldId);
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
            surveyMysqlDTO.setIsActive(subject.getIsActive());
            surveyMysqlDTO.setName(subject.getName());
            surveyMysqlDTO.setFieldId(subject.getFieldId());
            SubjectDTO subjectName = subjectNameMap.get(subject.getSubjectId());
            surveyMysqlDTO.setSubjectName(subjectName != null ? subjectName.getSubjectName() : null);
            surveyMysqlDTO.setTeacherName(subjectName != null ? subjectName.getTeacherName() : null);
            surveyMysqlDTO.setClassName(subjectName != null ? subjectName.getClassName() : null);
            return surveyMysqlDTO;
        }).collect(Collectors.toList());
    }
}
