package com.ems.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IsystemClient;
import com.ems.api.domain.dto.TeacherDTO;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.po.User;
import com.ems.api.domain.vo.StudentVO;
import com.ems.api.domain.vo.TeacherVO;
import com.ems.teacher.mapper.TeacherMapper;
import com.ems.teacher.service.ITeacherService;
import common.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private IsystemClient systemClient;

    @Override
    public Teacher selectByUsername(String username) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return teacherMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void addTeacherAndUser(Teacher teacher) {
        //判断用户名是否存在
        Teacher teacher1 = selectByUsername(teacher.getUsername());
        if (teacher1 != null) {
            throw new CommonException("用户已经存在",400);
        }
        User createdUser = new User();
        createdUser.setUsername(teacher.getUsername());
        createdUser.setRole(1);
        createdUser.setIsActive(1);
        createdUser.setPassword(String.valueOf(123456));
        systemClient.createUser(createdUser);
        teacherMapper.insert(teacher);
    }

    @Override
    public List<Teacher> selectByIds(List<Long> ids) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return teacherMapper.selectList(queryWrapper);
    }

    @Override
    public List<Teacher> selectByNames(List<String> names) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name", names);
        return teacherMapper.selectList(queryWrapper);
    }

    @Override
    public List<TeacherDTO> convertTeachers(List<Teacher> teachers) {

        return teachers.stream().map(teacher -> {
            TeacherDTO teacherDTO = new TeacherDTO();

            teacherDTO.setId(teacher.getId());
            teacherDTO.setUsername(teacher.getUsername());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setSex(teacher.getSex());
            teacherDTO.setBirthday(teacher.getBirthday());
            teacherDTO.setPhone(teacher.getPhone());
            teacherDTO.setEmail(teacher.getEmail());

            Date birthday = teacher.getBirthday();
            if (birthday != null) {
                LocalDate now = LocalDate.now();
                LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int age = Period.between(birthDate, now).getYears();
                teacherDTO.setAge(age);
            }
            return teacherDTO;
        }).collect(Collectors.toList());
    }

}
