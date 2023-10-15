package com.ems.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Classes;
import com.ems.api.domain.po.Teacher;
import com.ems.student.mapper.ClassesMapper;
import com.ems.student.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {

    @Autowired
    private ClassesMapper classesMapper;
    public List<Classes> selectByIds(List<Long> ids) {
        QueryWrapper<Classes> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return classesMapper.selectList(queryWrapper);
    }



}
