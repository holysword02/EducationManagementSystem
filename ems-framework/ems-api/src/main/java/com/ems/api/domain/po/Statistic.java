package com.ems.api.domain.po;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ems-statistic")
public class Statistic {
    @Id
    private String id;
    private JSONObject value;

}