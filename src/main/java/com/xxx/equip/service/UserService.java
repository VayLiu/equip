package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.pojo.LoginForm;
import com.xxx.equip.pojo.User;

/**
 * @Description: 用户业务层
 */
public interface UserService extends IService<User> {

    User login(LoginForm loginForm);

    IPage<User> getPageByParam(Page<User> page, User user);

    boolean checkUsername(String username);

    boolean checkPassword(Integer id, String oldPassword);

}
