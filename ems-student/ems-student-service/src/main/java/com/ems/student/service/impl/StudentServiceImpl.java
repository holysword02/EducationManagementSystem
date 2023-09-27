package com.ems.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.po.Student;
import com.ems.student.mapper.StudentMapper;
import com.ems.student.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

        private final StudentMapper studentMapper;

        @Override
        public StudentDTO getUserById(Long id){
            return studentMapper.getUserById(id);
        }

        @Override
        public List<StudentDTO> AllStudent(Long pageNum, Long pageSize){

            long OFFSET = Math.max((pageNum - 1) * pageSize, 0L);
            long LIMIT = pageSize;
            return studentMapper.AllStudent(LIMIT, OFFSET);
        }


}
