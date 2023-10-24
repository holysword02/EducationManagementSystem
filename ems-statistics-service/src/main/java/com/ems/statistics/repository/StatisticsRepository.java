package com.ems.statistics.repository;

import com.ems.api.domain.po.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsRepository extends MongoRepository<Statistic, String> {


}
