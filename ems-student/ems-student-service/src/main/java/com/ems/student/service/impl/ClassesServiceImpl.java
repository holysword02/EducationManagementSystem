package com.ems.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Classes;
import com.ems.student.mapper.ClassesMapper;
import com.ems.student.service.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {

        private final ClassesMapper classesMapper;


}
