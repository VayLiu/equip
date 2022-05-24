package com.xxx.equip.dto;

import com.xxx.equip.pojo.Equip;
import lombok.Data;

@Data
public class EquipDTO extends Equip {

    /**
     * 所属实验室名称
     */
    private String labName;
    /**
     * 申请人姓名
     */
    private String subName;
    /**
     * 当前登录用户类型
     */
    private Integer loginUserType;
    /**
     * 当前登录用户ID
     */
    private Integer loginUserId;
    /**
     * 当前登录教师的实验室ID
     */
    private Integer loginLabId;
}
