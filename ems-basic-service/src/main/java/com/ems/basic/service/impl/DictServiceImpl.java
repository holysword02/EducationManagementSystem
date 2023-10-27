package com.ems.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Dict;
import com.ems.basic.mapper.DictMapper;
import com.ems.basic.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Autowired
    private DictMapper dictMapper;

    public List<Dict> selectByIds(List<Long> ids) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        return dictMapper.selectList(queryWrapper);
    }

    public List<Dict> selectByNames(List<String> names) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("label", names);
        return dictMapper.selectList(queryWrapper);
    }

}
