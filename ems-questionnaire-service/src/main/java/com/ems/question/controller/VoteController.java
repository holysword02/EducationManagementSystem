package com.ems.question.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.VoteDTO;
import com.ems.api.domain.po.*;
import com.ems.api.domain.vo.StudentVO;
import com.ems.api.domain.vo.VoteVO;
import com.ems.question.service.ISurveyService;
import com.ems.question.service.IVoteService;
import common.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {
    @Autowired
    private IVoteService voteService;
    @Autowired
    private final ISurveyService surveyService;

    //根据用户id查询
    @GetMapping("/find")
    public VoteVO find(Integer pageNum, Integer pageSize) {
        IPage<VoteMysql> ip = new Page<>(pageNum, pageSize);
        QueryWrapper<VoteMysql> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", UserContext.getUser());
        List<VoteDTO> voteDTOS = voteService.convertVotes(voteService.page(ip,queryWrapper).getRecords());
        VoteVO voteVO = new VoteVO();
        voteVO.setRecords(voteDTOS);
        voteVO.setTotal(ip.getTotal());
        voteVO.setSize(ip.getSize());
        voteVO.setCurrent(ip.getCurrent());
        return voteVO;
    }

    //进行投票
    @PutMapping("/vote")
    public boolean vote(VoteDTO voteDTO) {
        VoteMysql voteMysql = new VoteMysql();
        voteMysql.setStatus(1);
        voteMysql.setStudentId(UserContext.getUser());
        voteMysql.setCreateDate(DateUtil.date());
        Vote vote = new Vote();
        voteMysql.setFieldId(vote.getId());
        vote.setSurveyId(voteDTO.getSurveyId());
        vote.setValue(voteDTO.getValue());
        voteService.save(voteMysql);
        voteService.vote(vote);
        return true;
    }



}
