package com.ems.statistics.controller;

import com.ems.api.domain.po.Statistic;
import com.ems.statistics.service.IStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    @Autowired
    private final IStatisticService statisticService;

    //统计
    @GetMapping("/test1")
    public boolean test1(@RequestParam String id , @RequestParam String surveyId) {
        return statisticService.aggregateEmsVotes(surveyId,id);
    }

    //查询结果
    @GetMapping("/test2/{id}")
    public Statistic test2(@PathVariable String id) {
        return statisticService.getStatistic(id);
    }

}
