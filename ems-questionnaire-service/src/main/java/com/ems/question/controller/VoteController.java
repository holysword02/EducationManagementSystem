package com.ems.question.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.client.IstudentClient;
import com.ems.api.domain.dto.VoteDTO;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.po.Vote;
import com.ems.api.domain.po.VoteMysql;
import com.ems.api.domain.vo.VoteVO;
import com.ems.question.service.IVoteService;
import common.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {
    @Autowired
    private final IstudentClient studentClient;
    @Autowired
    private IVoteService voteService;

    //根据用户id查询
    @GetMapping("/find")
    public VoteVO find(Integer pageNum, Integer pageSize) {
        IPage<VoteMysql> ip = new Page<>(pageNum, pageSize);
        Student student = studentClient.getByUsername(UserContext.getUser());
        QueryWrapper<VoteMysql> queryWrapper = new QueryWrapper<>();
        if (student == null) {
            return null;
        }
        queryWrapper.eq("student_id", student.getId());
        List<VoteDTO> voteDTOS = voteService.convertVotes(voteService.page(ip, queryWrapper).getRecords());
        VoteVO voteVO = new VoteVO();
        voteVO.setRecords(voteDTOS);
        voteVO.setTotal(ip.getTotal());
        voteVO.setSize(ip.getSize());
        voteVO.setCurrent(ip.getCurrent());
        return voteVO;
    }

    //进行投票
    @PutMapping("/vote")
    public boolean vote(@RequestBody VoteDTO voteDTO) {
        Vote vote = new Vote();
        vote.setValue(voteDTO.getValue());
        vote.setSurveyId(voteDTO.getSurveyId());
        Vote vote1 = voteService.vote(vote);
        VoteMysql voteMysql = new VoteMysql();
        voteMysql.setId(voteDTO.getId());
        voteMysql.setStatus(1);
        voteMysql.setCreateDate(DateUtil.date());
        voteMysql.setFieldId(vote1.getId());
        voteService.updateById(voteMysql);
        return true;
    }


}
