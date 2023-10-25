package com.ems.question.repository;

import com.ems.api.domain.po.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticRepository extends MongoRepository<Statistic, String> {
    // 可以在这里添加自定义的查询方法
}
