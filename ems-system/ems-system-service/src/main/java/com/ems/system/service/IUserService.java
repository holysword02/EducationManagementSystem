package com.ems.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.User;

import java.io.Serializable;


public interface IUserService extends IService<User> {

    boolean del(Serializable id, String cookie);

    boolean updatePassWd(User user, String cookie);

}
