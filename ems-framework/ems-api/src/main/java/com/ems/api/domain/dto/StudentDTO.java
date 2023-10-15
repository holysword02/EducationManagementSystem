package com.ems.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String username;
    private String name;
    private Integer sex;
    private Date birthday;
    private Integer age;
    private String phone;
    private String email;
    private Long classId;
    private String className;

}
