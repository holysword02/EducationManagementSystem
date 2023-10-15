package com.ems.subject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public List<SubjectVO> find(Integer pageNum, Integer pageSize) {
        IPage<Subject> ip = new Page<>(pageNum, pageSize);
        return subjectService.convertRecords(subjectService.page(ip).getRecords());
    }



    @GetMapping("/{id}")
    public ResponseEntity<SubjectVO> getSubject(@PathVariable Long id) {
        SubjectVO subjectVO = subjectService.getSubject(id);
        if (subjectVO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subjectVO, HttpStatus.OK);
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
    @DeleteMapping("/delete")
    public boolean delete(Long id) {
        return subjectService.removeById(id);
    }

}
