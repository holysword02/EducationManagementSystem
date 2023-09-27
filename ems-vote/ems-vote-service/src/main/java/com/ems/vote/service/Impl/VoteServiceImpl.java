package com.ems.vote.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Vote;
import com.ems.vote.mapper.VoteMapper;
import com.ems.vote.service.IVoteService;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements IVoteService {

}
