package com.ems.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Student;

import java.util.List;

public interface IStudentService extends IService<Student> {


    Student selectByUsername(String username);

    void addStudentAndUser(Student student);

    List<Student> selectByClassId(Long classId);

}
