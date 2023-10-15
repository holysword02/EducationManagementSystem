package com.ems.api.domain.vo;

import com.ems.api.domain.dto.StudentDTO;
import com.ems.api.domain.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVO {
    private List<TeacherDTO> records;
    private Long total;
    private Long size;
    private Long current;
}

