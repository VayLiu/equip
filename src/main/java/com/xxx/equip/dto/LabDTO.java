package com.xxx.equip.dto;

import com.xxx.equip.pojo.Lab;
import lombok.Data;

@Data
public class LabDTO extends Lab {

    /**
     * 实验室管理员姓名
     */
    private String managerName;
    /**
     * 所属教师姓名
     */
    private String teacherName;
    /**
     * 当前登录用户类型
     */
    private Integer loginUserType;
    /**
     * 当前登录用户ID
     */
    private Integer loginUserId;

}
