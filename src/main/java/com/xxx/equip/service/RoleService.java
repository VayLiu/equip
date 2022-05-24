package com.xxx.equip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.pojo.Role;

import java.util.List;

/**
 * @Description: 角色业务层
 */
public interface RoleService extends IService<Role> {

    List<Role> getRoles();
}
