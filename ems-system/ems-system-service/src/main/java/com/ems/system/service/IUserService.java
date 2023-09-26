package com.ems.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.User;

import java.io.Serializable;


public interface IUserService extends IService<User> {
    boolean insert(User user);

    boolean del(Serializable id, String cookie);

    boolean updateUser(User user, String cookie);

}
