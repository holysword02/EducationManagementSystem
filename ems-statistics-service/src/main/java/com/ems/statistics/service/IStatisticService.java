package com.ems.statistics.service;


import com.ems.api.domain.po.Statistic;

public interface IStatisticService{

    Statistic getStatistic(String id);

    boolean aggregateEmsVotes(String surveyId, String id);


}
