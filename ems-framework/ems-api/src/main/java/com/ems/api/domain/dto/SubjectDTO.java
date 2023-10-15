package com.ems.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Long id;
    private String subjectName;
    private Long subjectNameId;
    private String teacherName;
    private Long teacherId;
    private String className;
    private Long classId;
}