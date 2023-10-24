package com.ems.question.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.VoteMysql;
import com.ems.question.mapper.VoteMapper;
import com.ems.question.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl extends ServiceImpl<VoteMapper, VoteMysql> implements IVoteService {
}
