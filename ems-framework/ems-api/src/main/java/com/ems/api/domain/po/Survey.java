package com.ems.api.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import java.io.Serializable;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("students")
public class Survey implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String description;
    private Long subjectId;
    private Long userId;
    private Date createData;
    private Date endData;
    private Long questionId;
    private Integer isActive;


}