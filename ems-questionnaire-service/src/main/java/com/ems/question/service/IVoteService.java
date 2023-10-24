package com.ems.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.VoteDTO;
import com.ems.api.domain.po.Vote;
import com.ems.api.domain.po.VoteMysql;

import java.util.List;

public interface IVoteService extends IService<VoteMysql> {

    boolean vote(Vote vote);

    List<VoteDTO> convertVotes(List<VoteMysql> students);


}
