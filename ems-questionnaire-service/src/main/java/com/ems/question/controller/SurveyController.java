package com.ems.question.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.SurveyMysqlDTO;
import com.ems.api.domain.po.Dict;
import com.ems.api.domain.po.Survey;
import com.ems.api.domain.po.SurveyMysql;
import com.ems.api.domain.po.VoteMysql;
import com.ems.api.domain.vo.SurveyMysqlVO;
import com.ems.api.domain.vo.SurveyVO;
import com.ems.question.service.ISurveyService;
import com.ems.question.service.IVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    @Autowired
    private final ISurveyService surveyService;

    @Autowired
    private final IVoteService voteService;

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

    //根据id批量查询
    @PostMapping("/getByIds")
    public List<SurveyMysql> getByIds(@RequestBody List<Long> ids) {
        return surveyService.selectByIds(ids);
    }

    @GetMapping("/findone/{id}")
    public Survey getSurveyoneById(@PathVariable String id) {
        return surveyService.getSurvey(id);
    }

    //新建问卷
    @PostMapping("/newone")
    public boolean newSurvey(@RequestBody SurveyVO surveyvo) {
        List<VoteMysql> voteList = surveyService.newSurvey(surveyvo);
        return voteService.saveBatch(voteList);
    }

    //修改问卷
    @PutMapping("/updateone")
    public boolean updateSurvey(@RequestBody Survey Survey) {
        return surveyService.updateSurvey(Survey);
    }

    //删除问卷
    @DeleteMapping("/delete/{id}")
    public boolean deleteSurvey(@PathVariable String id) {
        return surveyService.deleteSurvey(id);
    }

    //修改详细
    @PutMapping("/update")
    public boolean updateSurveyone(@RequestBody SurveyMysql Surveymysql) {
        return surveyService.updateById(Surveymysql);
    }

    @GetMapping("/find")
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }





}
