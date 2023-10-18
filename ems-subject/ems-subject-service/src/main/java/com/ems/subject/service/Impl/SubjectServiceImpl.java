package com.ems.subject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IbasicClient;
import com.ems.api.client.IstudentClient;
import com.ems.api.client.IteacherClient;
import com.ems.api.domain.dto.SubjectDTO;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Dict;
import com.ems.api.domain.po.Subject;
import com.ems.api.domain.po.Teacher;
import com.ems.subject.mapper.SubjectMapper;
import com.ems.subject.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private IbasicClient ibasicClient;
    @Autowired
    private IstudentClient istudentClient;
    @Autowired
    private IteacherClient iteacherClient;

    @Override
    public SubjectDTO getSubject(Long id) {
        Subject subject = subjectMapper.selectById(id);
        if (subject == null) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());

        // 远程查询学科名称
        Dict dict = ibasicClient.getDict(subject.getSubjectNameId());
        subjectDTO.setSubjectName(dict.getLabel());

        // 远程查询教师名称
        Teacher teacher = iteacherClient.getTeacher(subject.getTeacherId());
        subjectDTO.setTeacherName(teacher.getName());

        // 远程查询班级名称
        Classes classes = istudentClient.getClass(subject.getClassId());
        subjectDTO.setClassName(classes.getName());

        return subjectDTO;
    }

    @Override
    public List<SubjectDTO> convertRecords(List<Subject> subjects) {
        List<Long> subjectNameIds = subjects.stream().map(Subject::getSubjectNameId).distinct().collect(Collectors.toList());
        List<Long> teacherIds = subjects.stream().map(Subject::getTeacherId).distinct().collect(Collectors.toList());
        List<Long> classIds = subjects.stream().map(Subject::getClassId).distinct().collect(Collectors.toList());

        List<Dict> subjectNames = ibasicClient.getDicts(subjectNameIds);
        List<Teacher> teachers = iteacherClient.getTeachers(teacherIds);
        List<Classes> classes = istudentClient.getClasses(classIds);

        Map<Long, Dict> subjectNameMap = subjectNames.stream().collect(Collectors.toMap(Dict::getId, Function.identity()));
        Map<Long, Teacher> teacherMap = teachers.stream().collect(Collectors.toMap(Teacher::getId, Function.identity()));
        Map<Long, Classes> classMap = classes.stream().collect(Collectors.toMap(Classes::getId, Function.identity()));

        return subjects.stream().map(subject -> {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject.getId());

            Dict subjectName = subjectNameMap.get(subject.getSubjectNameId());
            subjectDTO.setSubjectName(subjectName != null ? subjectName.getLabel() : null);

            Teacher teacher = teacherMap.get(subject.getTeacherId());
            subjectDTO.setTeacherName(teacher != null ? teacher.getName() : null);

            Classes clazz = classMap.get(subject.getClassId());
            subjectDTO.setClassName(clazz != null ? clazz.getName() : null);

            subjectDTO.setSubjectNameId(subject.getSubjectNameId());
            subjectDTO.setTeacherId(subject.getTeacherId());
            subjectDTO.setClassId(subject.getClassId());

            return subjectDTO;
        }).collect(Collectors.toList());
    }





}
