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
@TableName("equip_scrap")
public class EquipScrap {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备id
     */
    private String equipId;
    /**
     * 所属实验室id
     */
    private Integer labId;
    /**
     * 申请人id
     */
    private Integer applyId;
    /**
     * 审批人id
     */
    private Integer approveId;
    /**
     * 申请数量
     */
    private Integer quantity;
    /**
     * 申请状态
     * 0:待报废
     * 1:报废成功
     * 2:报废失败
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
