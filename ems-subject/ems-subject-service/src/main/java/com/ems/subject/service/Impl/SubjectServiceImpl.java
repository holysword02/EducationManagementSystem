package com.ems.subject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Subject;
import com.ems.subject.mapper.SubjectMapper;
import com.ems.subject.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
    
}
