package com.xxx.equip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "用户名长度为3-20个字符，由字母、数字、_（下划线）或-（短横线）组成")
    private String username;
    /**
     * 密码
     */
    @Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$" , message = "密码长度为6-20个字符，区分大小写")
    private String password;
    /**
     * 性别
     */
    private String gender;
    /**
     * 是否被删除
     */
    private Boolean isDeleted;
    /**
     * Email
     */
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 姓名
     */
    @Pattern(regexp = "^[a-zA-Z\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,20}$", message = "请输入有效的姓名")
    private String name;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 地址
     */
    private String address;
    /**
     * 手机号
     */
    private String telPhone;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 角色id
     */
    private Integer roleId;

}