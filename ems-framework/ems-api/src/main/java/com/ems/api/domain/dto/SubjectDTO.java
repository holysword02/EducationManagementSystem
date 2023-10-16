package com.ems.api.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String subjectName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectNameId;
    private String teacherName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long teacherId;
    private String className;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long classId;
}
