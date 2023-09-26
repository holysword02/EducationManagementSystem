package com.ems.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ems.api.domain.po.User;
import common.result.TokenData;

public interface ILoginService extends IService<User> {

    TokenData login(User user);

    User getUserInfoByToken(String token);
}
