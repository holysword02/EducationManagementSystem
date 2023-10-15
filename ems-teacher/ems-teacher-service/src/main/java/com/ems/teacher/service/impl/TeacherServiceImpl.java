package com.ems.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IsystemClient;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.po.User;
import com.ems.teacher.mapper.TeacherMapper;
import com.ems.teacher.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private IsystemClient systemClient;

    @Transactional
    public void addTeacherAndUser(Teacher teacher) {
        User createdUser = new User();
        createdUser.setUsername(teacher.getUsername());
        createdUser.setRole(1);
        createdUser.setIsActive(1);
        createdUser.setPassword(String.valueOf(123456));
        systemClient.createUser(createdUser);
        teacherMapper.insert(teacher);
    }

    public List<Teacher> selectByIds(List<Long> ids) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return teacherMapper.selectList(queryWrapper);
    }

}
