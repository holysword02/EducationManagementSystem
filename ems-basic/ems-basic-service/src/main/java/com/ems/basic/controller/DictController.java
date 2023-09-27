package com.ems.basic.controller;

import com.ems.basic.service.IDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final IDictService dictService;

    //    新增
    public void add() {
    }
    //    删除
    public void delete() {
    }
    //    修改
    public void update() {
    }
    //    查询
    public void query() {
    }
}
