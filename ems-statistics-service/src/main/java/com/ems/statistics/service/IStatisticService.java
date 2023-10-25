package com.ems.statistics.service;


import com.ems.api.domain.po.Statistic;

public interface IStatisticService{

    Statistic getStatistic(String id);

    Statistic aggregateEmsVotes(String surveyId, String statisticId);


}
