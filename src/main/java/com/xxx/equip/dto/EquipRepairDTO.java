package com.xxx.equip.dto;

import com.xxx.equip.pojo.EquipRepair;
import lombok.Data;

@Data
public class EquipRepairDTO extends EquipRepair {

    /**
     * 设备名称
     */
    private String equipName;
    /**
     * 所属实验室名称
     */
    private String labName;
    /**
     * 申请人姓名
     */
    private String applyName;
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
