package com.ems.subject.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.client.IbasicClient;
import com.ems.api.client.IstudentClient;
import com.ems.api.client.IteacherClient;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Dict;
import com.ems.api.domain.po.Subject;
import com.ems.api.domain.po.Teacher;
import com.ems.api.domain.vo.SubjectVO;
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

    public SubjectVO getSubject(Long id) {
        Subject subject = subjectMapper.selectById(id);
        if (subject == null) {
            return null;
        }

        SubjectVO subjectVO = new SubjectVO();
        subjectVO.setId(subject.getId());

        // 远程查询学科名称
        Dict dict = ibasicClient.getDict(subject.getSubjectNameId());
        subjectVO.setSubjectName(dict.getLabel());

        // 远程查询教师名称
        Teacher teacher = iteacherClient.getTeacher(subject.getTeacherId());
        subjectVO.setTeacherName(teacher.getName());

        // 远程查询班级名称
        Classes classes = istudentClient.getClass(subject.getClassId());
        subjectVO.setClassName(classes.getName());

        return subjectVO;
    }

    public List<SubjectVO> convertRecords(List<Subject> subjects) {
        List<Long> subjectNameIds = subjects.stream().map(Subject::getSubjectNameId).collect(Collectors.toList());
        List<Long> teacherIds = subjects.stream().map(Subject::getTeacherId).collect(Collectors.toList());
        List<Long> classIds = subjects.stream().map(Subject::getClassId).collect(Collectors.toList());

        List<Dict> subjectNames = ibasicClient.getDicts(subjectNameIds);
        List<Teacher> teachers = iteacherClient.getTeachers(teacherIds);
        List<Classes> classes = istudentClient.getClasses(classIds);

        Map<Long, Dict> subjectNameMap = subjectNames.stream().collect(Collectors.toMap(Dict::getId, Function.identity()));
        Map<Long, Teacher> teacherMap = teachers.stream().collect(Collectors.toMap(Teacher::getId, Function.identity()));
        Map<Long, Classes> classMap = classes.stream().collect(Collectors.toMap(Classes::getId, Function.identity()));

        return subjects.stream().map(subject -> {
            SubjectVO subjectVO = new SubjectVO();
            subjectVO.setId(subject.getId());
            subjectVO.setSubjectName(subjectNameMap.get(subject.getSubjectNameId()).getLabel());
            subjectVO.setTeacherName(teacherMap.get(subject.getTeacherId()).getName());
            subjectVO.setClassName(classMap.get(subject.getClassId()).getName());

            return subjectVO;
        }).collect(Collectors.toList());
    }





}
