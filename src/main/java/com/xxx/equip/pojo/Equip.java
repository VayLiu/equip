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
@TableName("equipment")
public class Equip {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备名
     */
    private String name;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private Double price;
    /**
     * 生产日期
     */
    private Date datePro;
    /**
     * 产地
     */
    private String origin;
    /**
     * 所属实验室id
     */
    private Integer labId;
    /**
     * 申购人id
     */
    private Integer subId;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

}
