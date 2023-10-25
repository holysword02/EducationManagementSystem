package com.ems.statistics.service.Impl;

import com.ems.api.domain.po.Statistic;
import com.ems.statistics.repository.StatisticsRepository;
import com.ems.statistics.repository.VoteRepository;
import com.ems.statistics.service.IStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private VoteRepository voteRepository;


    //查询统计信息
    @Override
    public Statistic getStatistic(String id) {
        return statisticsRepository.findById(id).orElse(null);
    }

    @Override
    //创建并查询统计信息
    public Statistic aggregateEmsVotes(Long surveyId, String statisticId) {
        voteRepository.aggregateEmsVotes(surveyId, statisticId);
        return getStatistic(statisticId);
    }


}
