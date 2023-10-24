package com.ems.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.TeacherDTO;
import com.ems.api.domain.po.Teacher;

import java.util.List;

public interface ITeacherService extends IService<Teacher> {

    void addTeacherAndUser(Teacher teacher);

    List<Teacher> selectByIds(List<Long> ids);

    List<Teacher> selectByNames(List<String> names);

    Teacher selectByUsername(String username);

    List<TeacherDTO> convertTeachers(List<Teacher> teachers);
}
