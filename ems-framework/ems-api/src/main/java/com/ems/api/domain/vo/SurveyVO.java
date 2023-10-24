package com.ems.api.domain.vo;

import cn.hutool.json.JSONArray;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyVO {
    private JSONArray value;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectId;
    private Date endDate;
}
