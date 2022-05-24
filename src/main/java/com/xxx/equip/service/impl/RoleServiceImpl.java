package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.mapper.RoleMapper;
import com.xxx.equip.pojo.Role;
import com.xxx.equip.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 角色业务层实现类
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> getRoles() {
        QueryWrapper queryWrapper = new QueryWrapper();
        return baseMapper.selectList(queryWrapper);
    }
}
