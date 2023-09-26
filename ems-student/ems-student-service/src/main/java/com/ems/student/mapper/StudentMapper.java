package com.ems.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    //查询学生及班级信息
    @Select("SELECT " +
            "u.id, " +
            "u.username, " +
            "u.name, " +
            "u.sex, " +
            "u.birthday, " +
            "TIMESTAMPDIFF(YEAR, u.birthday, CURDATE()) AS age, " +
            "u.phone, " +
            "u.email, " +
            "c.name AS classname " +
            "FROM students u " +
            "LEFT JOIN classes c ON u.class_id = c.id " +
            "WHERE u.id = #{id}")
    StudentDTO getUserById(@Param("id") Long id);

    @Select("SELECT " +
            "u.id, " +
            "u.username, " +
            "u.name, " +
            "u.sex, " +
            "u.birthday, " +
            "TIMESTAMPDIFF(YEAR, u.birthday, CURDATE()) AS age, " +
            "u.phone, " +
            "u.email, " +
            "c.name AS classname " +
            "FROM students u " +
            "LEFT JOIN classes c ON u.class_id = c.id " +
            "LIMIT #{limit} OFFSET #{offset}")
    List<StudentDTO> AllStudent(@Param("limit") Integer LIMIT, @Param("offset") Integer OFFSET);
}
