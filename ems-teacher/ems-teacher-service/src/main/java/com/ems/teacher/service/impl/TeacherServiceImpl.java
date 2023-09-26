package com.ems.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Teacher;
import com.ems.teacher.mapper.TeacherMapper;
import com.ems.teacher.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    private final TeacherMapper teacherMapper;
    public void updateTeacher(Teacher teacher) {
        // 使用 MyBatis Plus 提供的 updateById 方法来更新教师信息
        teacherMapper.updateById(teacher);
    }
}