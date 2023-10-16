package com.ems.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Student;
import com.ems.api.domain.vo.StudentVO;
import com.ems.student.mapper.ClassesMapper;
import com.ems.student.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {

    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public List<Classes> selectByIds(List<Long> ids) {
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return classesMapper.selectList(queryWrapper);
    }

    @Override
    public List<Classes> selectByNames(List<String> names) {
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name", names);
        return classesMapper.selectList(queryWrapper);
    }

    @Override
    public List<StudentDTO> convertStudents(List<Student> students) {

        List<Long> classIds = students.stream().map(Student::getClassId).distinct().collect(Collectors.toList());
        List<Classes> classes = selectByIds(classIds);
        Map<Long, Classes> classMap = classes.stream().collect(Collectors.toMap(Classes::getId, Function.identity()));

        return students.stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            // ... copy other fields ...
            Classes clazz = classMap.get(student.getClassId());
            studentDTO.setClassName(clazz != null ? clazz.getName() : null);
            studentDTO.setId(student.getId());
            studentDTO.setUsername(student.getUsername());
            studentDTO.setName(student.getName());
            studentDTO.setSex(student.getSex());
            studentDTO.setBirthday(student.getBirthday());
            studentDTO.setPhone(student.getPhone());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setClassId(student.getClassId());
            Date birthday = student.getBirthday();
            if (birthday != null) {
                LocalDate now = LocalDate.now();
                LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int age = Period.between(birthDate, now).getYears();
                studentDTO.setAge(age);
            }
            return studentDTO;
        }).collect(Collectors.toList());
    }



}
