<<<<<<<< HEAD:ems-framework/ems-api/src/main/java/com/ems/api/domain/po/Survey.java
package com.ems.api.domain.po;
========
package com.ems.basic.entity;
>>>>>>>> b5a8045687737833598a82825c67d9a356de1ba4:ems-basic/ems-basic-service/src/main/java/com/ems/basic/entity/Class.java

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("students")
public class Survey implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Long parentId;
    private Long questionId;
    private Integer orderby;

}