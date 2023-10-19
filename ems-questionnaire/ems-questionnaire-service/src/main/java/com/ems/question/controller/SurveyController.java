package com.ems.question.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ems.api.domain.po.Survey;
import com.ems.question.service.ISurveyService;
import com.ems.question.service.MSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final MSurveyService surveyService;

    @PostMapping("/create")
    public Survey createSurvey(@RequestBody Survey Survey) {
        return surveyService.createSurvey(Survey);
    }

    @GetMapping("/find")
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurvey(id);
    }

}
