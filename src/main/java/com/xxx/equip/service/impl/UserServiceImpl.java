package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.mapper.UserMapper;
import com.xxx.equip.pojo.LoginForm;
import com.xxx.equip.pojo.User;
import com.xxx.equip.service.UserService;
import com.xxx.equip.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: 用户业务层实现类
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(LoginForm loginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",loginForm.getRole());
        queryWrapper.eq("username",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> getPageByParam(Page<User> page, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",user.getRoleId());
        if (!StringUtils.isEmpty(user.getUsername())) {
            queryWrapper.eq("username", user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getName())) {
            queryWrapper.like("name", user.getName());
        }
        queryWrapper.orderByAsc("id");
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 检验用户名是否可用
     *
     * @param username 用户名
     * @return true：代表用户名可用 false：代表不可用
     */
    @Override
    public boolean checkUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return 0 == baseMapper.selectCount(queryWrapper);
    }

    @Override
    public boolean checkPassword(Integer id, String oldPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("password", MD5.encrypt(oldPassword));
        return 1 == baseMapper.selectCount(queryWrapper);
    }

}
