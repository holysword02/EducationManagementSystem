package com.ems.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<StudentVO> convertStudents(List<Student> students) {

        List<Long> classIds = students.stream().map(Student::getClassId).collect(Collectors.toList());
        List<Classes> classes = selectByIds(classIds);
        Map<Long, Classes> classMap = classes.stream().collect(Collectors.toMap(Classes::getId, Function.identity()));

        return students.stream().map(student -> {
            StudentVO studentVO = new StudentVO();
            // ... copy other fields ...
            Classes clazz = classMap.get(student.getClassId());
            studentVO.setClassName(clazz != null ? clazz.getName() : null);
            studentVO.setId(student.getId());
            studentVO.setUsername(student.getUsername());
            studentVO.setName(student.getName());
            studentVO.setSex(student.getSex());
            studentVO.setBirthday(student.getBirthday());
            studentVO.setPhone(student.getPhone());
            studentVO.setEmail(student.getEmail());
            studentVO.setClassId(student.getClassId());
            Date birthday = student.getBirthday();
            if (birthday != null) {
                LocalDate now = LocalDate.now();
                LocalDate birthDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int age = Period.between(birthDate, now).getYears();
                studentVO.setAge(age);
            }
            return studentVO;
        }).collect(Collectors.toList());
    }



}
