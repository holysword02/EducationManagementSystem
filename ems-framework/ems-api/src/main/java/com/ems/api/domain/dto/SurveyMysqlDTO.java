package com.ems.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyMysqlDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String fieldId;
    private Long subjectId;
    private String subjectName;
    private String teacherName;
    private String className;
    private Date createDate;
    private Date endDate;
    private Integer isActive;

}
