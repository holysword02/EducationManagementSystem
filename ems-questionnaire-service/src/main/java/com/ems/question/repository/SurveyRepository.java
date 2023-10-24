package com.ems.question.repository;

import com.ems.api.domain.po.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey, String> {
    // 可以在这里添加自定义的查询方法
}
