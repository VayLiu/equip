package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.pojo.Admin;
import com.xxx.equip.pojo.LoginForm;

/**
 * @Description: 管理员业务层
 */
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    IPage<Admin> getPageByParam(Page<Admin> page, String name);

    boolean checkUsername(String username);

    boolean checkPassword(Integer id, String oldPassword);
}
