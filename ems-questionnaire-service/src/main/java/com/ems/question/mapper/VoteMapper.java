package com.ems.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.api.domain.po.VoteMysql;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteMapper extends BaseMapper<VoteMysql> {
}
