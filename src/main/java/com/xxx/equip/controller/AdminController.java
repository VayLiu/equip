package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.pojo.Admin;
import com.xxx.equip.service.AdminService;
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

@Api(tags = "管理员控制")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ApiOperation("验证用户名")
    @PostMapping("/checkUsername")
    public Result checkUsername(
            @ApiParam("JSON对象") @RequestBody HashMap<String, String> map
    ) {
        String regUsername = "^[a-zA-Z0-9_-]{3,16}$";
        String username = map.get("username");
        if (!username.matches(regUsername)) {
            return Result.fail().message("长度为3-20个字符，由字母、数字、_（下划线）或-（短横线）组成");
        }
        boolean b = adminService.checkUsername(username);
        if (b) {
            return Result.success().message("用户名可用");
        } else {
            return Result.fail().message("用户名不可用");
        }
    }


    @ApiOperation("更新密码")
    @PostMapping("/updatePassword")
    public Result updatePassword(
            @ApiParam("JSON对象") @RequestBody HashMap<String, String> map
    ) {
        String newPassword = map.get("newPassword");
        String renewPassword = map.get("renewPassword");
        if (renewPassword.equals(newPassword)) {
            Integer id = Integer.valueOf(map.get("id"));
            String oldPassword = map.get("oldPassword");
            boolean b = adminService.checkPassword(id, oldPassword);
            if (b) {
                Admin admin = new Admin();
                admin.setId(id);
                admin.setPassword(MD5.encrypt(renewPassword));
                adminService.saveOrUpdate(admin);
                return Result.success().message("密码修改成功！");
            } else {
                return Result.fail().message("原始密码输入错误！");
            }
        } else {
            return Result.fail().message("新密码与重复密码密码输入不一致！");
        }

    }

    @ApiOperation("新增或修改，有ID属性是修改，没有则是增加")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(
            @ApiParam("要新增或修改的JSON的对象") @RequestBody Admin admin,
            BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            Map<String, Object> map = new LinkedHashMap<>();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Result.fail(map);
        } else {
            Date date = new Date();
            if (null == admin.getId()) {
                admin.setCreatedAt(date);
                admin.setUpdatedAt(date);
            } else {
                admin.setUpdatedAt(date);
            }
            if (null != admin.getPassword())
                admin.setPassword(MD5.encrypt(admin.getPassword()));
            adminService.saveOrUpdate(admin);
            return Result.success();
        }
    }

    @ApiOperation("批量删除信息")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam("要删除的所有id的JSON集合") @RequestBody List<Integer> ids
    ) {
        adminService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("根据id查询个体")
    @GetMapping("/getOne/{id}")
    public Result getOne(
            @ApiParam("id") @PathVariable Integer id
    ) {
        Admin admin = adminService.getById(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("admin", admin);
        return Result.success(map);
    }

    @ApiOperation("根据姓名模糊分页查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getAll(
            @ApiParam("分页查询的页码") @PathVariable Integer pageNumber,
            @ApiParam("分页查询的页大小") @PathVariable Integer pageSize,
            @ApiParam("分页查询模糊匹配的姓名") String name
    ) {
        Page<Admin> page = new Page<>(pageNumber, pageSize);
        IPage<Admin> iPage = adminService.getPageByParam(page, name);
        return Result.success(iPage);
    }

}
