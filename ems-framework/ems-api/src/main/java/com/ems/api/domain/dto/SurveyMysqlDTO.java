package com.ems.api.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyMysqlDTO implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String description;
    private String fieldId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;
    private String subjectName;
    private String teacherName;
    private String className;
    private Date createDate;
    private Date endDate;
    private Integer isActive;

}
