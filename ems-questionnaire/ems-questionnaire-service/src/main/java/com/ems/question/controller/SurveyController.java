package com.ems.question.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.SurveyMysqlDTO;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.po.Survey;
import com.ems.api.domain.po.SurveyMysql;
import com.ems.api.domain.vo.StudentVO;
import com.ems.api.domain.vo.SurveyMysqlVO;
import com.ems.api.domain.vo.SurveyVO;
import com.ems.question.service.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final ISurveyService surveyService;

    //分页查询
    @GetMapping("/findAll")
    public SurveyMysqlVO find(Integer pageNum, Integer pageSize) {
        IPage<SurveyMysql> ip = new Page<>(pageNum, pageSize);
        List<SurveyMysqlDTO> studentDTOS = surveyService.convertRecords(surveyService.page(ip).getRecords());
        SurveyMysqlVO studentVO = new SurveyMysqlVO();
        studentVO.setRecords(studentDTOS);
        studentVO.setTotal(ip.getTotal());
        studentVO.setSize(ip.getSize());
        studentVO.setCurrent(ip.getCurrent());
        return studentVO;
    }


    @PostMapping("/add")
    public boolean createSurvey(@RequestBody Survey Survey) {
        return surveyService.createSurvey(Survey);
    }

    //修改
    @PutMapping("/update")
    public boolean updateSurvey(@RequestBody Survey Survey) {
        return surveyService.updateSurvey(Survey);
    }

    @GetMapping("/find")
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/find/{id}")
    public SurveyVO getSurveyById(@PathVariable String id) {
        return surveyService.getSurveyById(id);
    }

    @GetMapping("/findone/{id}")
    public Survey getSurveyoneById(@PathVariable String id) {
        return surveyService.getSurvey(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSurvey(@PathVariable String id) {
        surveyService.deleteSurvey(id);
    }

}
