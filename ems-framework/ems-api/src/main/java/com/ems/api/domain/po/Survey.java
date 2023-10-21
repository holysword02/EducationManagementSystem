package com.ems.api.domain.po;

import cn.hutool.json.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ems-survey")
public class Survey {
    @Id
    private String id;
    private JSONArray value;

}