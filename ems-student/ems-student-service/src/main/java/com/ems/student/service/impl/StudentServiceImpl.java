package com.ems.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IsystemClient;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.po.User;
import com.ems.student.mapper.StudentMapper;
import com.ems.student.service.IStudentService;
import common.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Autowired
    private IsystemClient systemClient;

    @Override
    public Student selectByUsername(String username) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return studentMapper.selectOne(queryWrapper);
    }


    @Override
    @Transactional
    public void addStudentAndUser(Student student) {
        //判断用户是否已经存在
        Student student1 = selectByUsername(student.getUsername());
        if (student1 != null) {
            throw new CommonException("用户已经存在",400);
        }
        User createdUser = new User();
        createdUser.setUsername(student.getUsername());
        createdUser.setRole(2);
        createdUser.setIsActive(1);
        createdUser.setPassword(String.valueOf(123456));
        systemClient.createUser(createdUser);
        studentMapper.insert(student);
    }




}
