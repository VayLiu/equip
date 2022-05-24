package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.pojo.User;
import com.xxx.equip.service.UserService;
import com.xxx.equip.util.MD5;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "用户控制")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("统计用户数")
    @GetMapping("/getCount")
    public Result getCount(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (null != user.getRoleId()) {
            queryWrapper.eq("role_id", user.getRoleId());
        }
        Integer num = userService.count(queryWrapper);
        return Result.success(num);
    }

}
