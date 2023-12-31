package com.ems.statistics.repository;

import com.ems.api.domain.po.Vote;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String> {

    @Aggregation(pipeline = {
            "{ $match: { surveyId: ?0 } }",
            "{ $project: { values: { $objectToArray: '$value' } } }",
            "{ $unwind: '$values' }",
            "{ $unwind: '$values.v' }",
            "{ $group: { _id: { key: '$values.k', value: '$values.v' }, count: { $sum: 1 } } }",
            "{ $group: { _id: '$_id.key', values: { $push: { k: '$_id.value', v: '$count' } } } }",
            "{ $replaceRoot: { newRoot: { $mergeObjects: [ { question: '$_id' }, { value: { $arrayToObject: '$values' } } ] } } }",
            "{ $group: { _id: ?1, values: { $push: { question: '$question', value: '$value' } } } }",
            "{ $replaceRoot: { newRoot: { $mergeObjects: [ { _id: '$_id' }, { value: '$values' } ] } } }",
            "{ $merge: { into: 'ems-statistic', on: '_id', whenMatched: 'replace', whenNotMatched: 'insert' } }"
    })
    void aggregateEmsVotes(Long surveyId, String id);
}
