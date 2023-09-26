package com.ems.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Teacher;
import com.ems.teacher.mapper.TeacherMapper;
import com.ems.teacher.service.ITeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
