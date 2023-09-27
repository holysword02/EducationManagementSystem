package com.ems.vote.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.api.domain.po.Vote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteMapper extends BaseMapper<Vote> {

}
