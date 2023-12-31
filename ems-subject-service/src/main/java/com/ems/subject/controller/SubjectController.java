package com.ems.subject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.Subject;
import com.ems.api.domain.vo.SubjectVO;
import com.ems.subject.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final ISubjectService subjectService;

    //分页查询
    @GetMapping("/find")
    public SubjectVO find(Integer pageNum, Integer pageSize) {
        IPage<Subject> ip = new Page<>(pageNum, pageSize);
        List<SubjectDTO> subjectDTOS = subjectService.convertRecords(subjectService.page(ip).getRecords());
        SubjectVO subjectVO = new SubjectVO();
        subjectVO.setRecords(subjectDTOS);
        subjectVO.setTotal(ip.getTotal());
        subjectVO.setSize(ip.getSize());
        subjectVO.setCurrent(ip.getCurrent());
        return subjectVO;
    }

    //查询全部
    @GetMapping("/findAll")
    public List<SubjectDTO> findAll() {
        List<Subject> subjects = subjectService.list();
        return subjectService.convertRecords(subjects);
    }

    //根据id批量查询
    @PostMapping("/getByIds")
    public List<SubjectDTO> getByIds(@RequestBody List<Long> ids) {
        List<Subject> subjects = subjectService.selectByIds(ids);
        return subjectService.convertRecords(subjects);
    }

    //根据id查询
    @GetMapping("/getById")
    public Subject getById(Long id) {
        return subjectService.getById(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
        SubjectDTO subjectDTO = subjectService.getSubject(id);
        if (subjectDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }


    //新增
    @PostMapping("/add")
    public boolean add(@RequestBody Subject subject) {
        return subjectService.save(subject);
    }

    //修改
    @PutMapping("/update")
    public boolean update(@RequestBody Subject subject) {
        return subjectService.updateById(subject);
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return subjectService.removeById(id);
    }

}
