package com.ems.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.vo.StudentVO;

import java.util.List;

public interface IClassesService extends IService<Classes> {

    List<Classes> selectByIds(List<Long> ids);

    List<Classes> selectByNames(List<String> names);

    List<StudentDTO> convertStudents(List<Student> students);

}
