package com.xxx.equip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("admin")
public class Admin {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色
     * default：普通管理员
     * admin：超级管理员
     */
    private String role;
    /**
     * 用户名
     */
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "用户名长度为3-20个字符，由字母、数字、_（下划线）或-（短横线）组成")
    private String username;
    /**
     * 密码
     */
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$" , message = "密码长度为6-20个字符，区分大小写")
    private String password;
    /**
     * 姓名
     */
    @Pattern(regexp = "^[a-zA-Z\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,20}$", message = "请输入有效的姓名")
    private String name;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

}