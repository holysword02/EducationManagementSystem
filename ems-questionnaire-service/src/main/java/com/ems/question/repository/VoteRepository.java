package com.ems.question.repository;

import com.ems.api.domain.po.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {
}
