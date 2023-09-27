package com.ems.basic.controller;

import com.ems.basic.entity.Dict;
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

    //    新增
    @PostMapping("/save")
    public boolean insert(Dict dict) {
        return dictService.save(dict);
    }

    //    删除
    @DeleteMapping("/delete/{id}")
    public boolean del(@PathVariable Serializable id) {
        return dictService.removeById(id);
    }

    //    修改
    @PutMapping("/update")
    public boolean update(Dict dict) {
        return dictService.updateById(dict);
    }

    //    查询
    @GetMapping("/list")
    public List<Dict> getAll() {
        return dictService.list();
    }
}
