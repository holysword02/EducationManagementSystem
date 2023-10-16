package com.ems.api.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String name;
    private Integer sex;
    private Date birthday;
    private Integer age;
    private String phone;
    private String email;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long classId;
    private String className;

}
