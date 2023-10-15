package com.ems.api.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectVO {
    private Long id;
    private String subjectName;
    private String teacherName;
    private String className;
}
