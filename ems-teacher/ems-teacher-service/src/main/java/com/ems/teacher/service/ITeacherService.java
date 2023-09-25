package com.ems.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.teacher.entity.Teacher;

public interface ITeacherService extends IService<Teacher> {

    void updateTeacher(Teacher teacher);

}
