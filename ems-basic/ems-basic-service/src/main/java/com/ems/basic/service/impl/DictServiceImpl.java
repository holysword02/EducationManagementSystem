package com.ems.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ems.api.domain.po.Dictionary;
import com.ems.basic.mapper.DictMapper;
import com.ems.basic.service.IDictService;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dictionary> implements IDictService {

}
