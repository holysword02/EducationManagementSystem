package com.ems.api.domain.vo;

import cn.hutool.json.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyVO {
    private String id;
    private JSONArray value;
    private String name;
    private String description;
    private String fieldId;
    private Long subjectId;
    private Date creatDate;
    private Date endDate;
    private Integer isActive;
}
