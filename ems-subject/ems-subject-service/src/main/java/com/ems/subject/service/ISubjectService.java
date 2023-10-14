package com.ems.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Subject;
import com.ems.api.domain.vo.SubjectVO;

import java.util.List;

public interface ISubjectService extends IService<Subject> {

    SubjectVO getSubject(Long id);

    List<SubjectVO> convertRecords(List<Subject> subjects);

}
