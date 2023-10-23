package com.ems.statistics.service.Impl;

import com.ems.api.domain.po.Statistic;
import com.ems.statistics.repository.StatisticsRepository;
import com.ems.statistics.repository.VoteRepository;
import com.ems.statistics.service.IStatisticService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    //创建统计信息
    public boolean aggregateEmsVotes(String surveyId, String id) {
        voteRepository.aggregateEmsVotes(surveyId, id);
        return true;
    }


}
