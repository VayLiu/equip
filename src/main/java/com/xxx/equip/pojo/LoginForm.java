package com.xxx.equip.pojo;

import lombok.Data;

/**
 * 用户登录表单信息
 */
@Data
public class LoginForm {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 角色类型
     */
    private Integer role;
}
