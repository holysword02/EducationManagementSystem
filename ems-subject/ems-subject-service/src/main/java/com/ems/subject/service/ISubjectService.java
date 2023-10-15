package com.ems.subject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.Subject;
import com.ems.api.domain.vo.SubjectVO;

import java.util.List;

public interface ISubjectService extends IService<Subject> {

    SubjectDTO getSubject(Long id);

    List<SubjectDTO> convertRecords(List<Subject> subjects);

}
