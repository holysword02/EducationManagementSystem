package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.dto.VoteDTO;
import com.ems.api.domain.po.SurveyMysql;
import com.ems.api.domain.po.Vote;
import com.ems.api.domain.po.VoteMysql;
import com.ems.question.mapper.VoteMapper;
import com.ems.question.repository.VoteRepository;
import com.ems.question.service.ISurveyService;
import com.ems.question.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, VoteMysql> implements IVoteService {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ISurveyService surveyService;


    //进行投票
    public boolean vote(Vote vote) {
        voteRepository.save(vote);
        return true;
    }


    @Override
    public List<VoteDTO> convertVotes(List<VoteMysql> students) {

        List<Long> classIds = students.stream().map(VoteMysql::getSurveyId).distinct().collect(Collectors.toList());
        List<SurveyMysql> classes = surveyService.selectByIds(classIds);
        Map<Long, SurveyMysql> classMap = classes.stream().collect(Collectors.toMap(SurveyMysql::getId, Function.identity()));

        return students.stream().map(student -> {
            VoteDTO studentDTO = new VoteDTO();
            // ... copy other fields ...
            SurveyMysql clazz = classMap.get(student.getSurveyId());
            studentDTO.setName(clazz != null ? clazz.getName() : null);
            studentDTO.setId(student.getId());
            studentDTO.setFieldId(student.getFieldId());
            studentDTO.setSurveyId(student.getSurveyId());
            studentDTO.setCreateDate(student.getCreateDate());
            studentDTO.setStudentId(student.getStudentId());
            return studentDTO;
        }).collect(Collectors.toList());
    }


}
