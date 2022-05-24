package com.xxx.equip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("equip_sub")
public class EquipSub {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备名
     */
    private String equipName;
    /**
     * 数量
     */
    private Integer equipQuantity;
    /**
     * 单价
     */
    private Double equipPrice;
    /**
     * 生产日期
     */
    private Date equipDatePro;
    /**
     * 产地
     */
    private String equipOrigin;
    /**
     * 申购人id
     */
    private Integer subId;
    /**
     * 所属实验室id
     */
    private Integer labId;
    /**
     * 审批人id
     */
    private Integer approveId;
    /**
     * 状态
     * 0:待通过
     * 1:已通过
     */
    private Integer state;
    /**
     * 申请理由
     */
    private String reason;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

}
