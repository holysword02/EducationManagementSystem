package com.ems.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.po.User;

import java.util.List;

public interface ITeacherService extends IService<Teacher> {

    void addTeacherAndUser(Teacher teacher);

    List<Teacher> selectByIds(List<Long> ids);
}
