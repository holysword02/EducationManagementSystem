package com.ems.api.domain.po;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ems-statistic")
public class Statistic {
    @Id
    private String id;
    private JSONArray value;

}