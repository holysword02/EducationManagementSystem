package com.ems.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.Classes;

import java.util.List;

public interface IClassesService extends IService<Classes> {

    List<Classes> selectByIds(List<Long> ids);
}
