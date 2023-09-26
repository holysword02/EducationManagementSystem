package com.ems.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentService extends IService<Student> {

    StudentDTO getUserById(@Param("id") Long id);


    List<StudentDTO> AllStudent(Integer pageNum, Integer pageSize);
}
