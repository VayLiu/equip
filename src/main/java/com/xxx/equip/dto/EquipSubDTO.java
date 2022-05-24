package com.xxx.equip.dto;

import com.xxx.equip.pojo.EquipSub;
import lombok.Data;

@Data
public class EquipSubDTO extends EquipSub {
    
    /**
     * 所属实验室名称
     */
    private String labName;
    /**
     * 申购人姓名
     */
    private String subName;
    /**
     * 审批人姓名
     */
    private String approveName;
    /**
     * 当前登录用户类型
     */
    private Integer loginUserType;
    /**
     * 当前登录用户ID
     */
    private Integer loginUserId;
}
