package com.ems.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ems.api.domain.po.Dict;
import com.ems.basic.service.IDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final IDictService dictService;

    //根据id
    @GetMapping("/getById")
    public Dict getById(Long id) {
        return dictService.getById(id);
    }

    //根据id批量查询
    @PostMapping("/getByIds")
    public List<Dict> getByIds(@RequestBody List<Long> ids) {
        return dictService.selectByIds(ids);
    }

    //根据name批量查询
    @PostMapping("/getByNames")
    public List<Dict> getByNames(@RequestBody List<String> names) {
        return dictService.selectByNames(names);
    }

    //    新增
    @PostMapping("/insert")
    public boolean insert(@RequestBody Dict dict) {
        return dictService.save(dict);
    }

    //    删除
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id) {
        return dictService.removeById(id);
    }

    //    修改
    @PutMapping("/update")
    public boolean update(@RequestBody Dict dict) {
        return dictService.updateById(dict);
    }

    //    查询学科
    @GetMapping("/list/subject")
    public List<Dict> getSubject() {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        qw.eq("is_active", 1);
        return dictService.list(qw);
    }

}
