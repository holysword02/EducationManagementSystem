package com.ems.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Dict;

import java.util.List;


public interface IDictService extends IService<Dict> {

    List<Dict> selectByIds(List<Long> ids);
}
