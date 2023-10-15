package com.ems.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Student;

public interface IStudentService extends IService<Student> {



    Student selectByUsername(String username);

    void addStudentAndUser(Student student);

}
