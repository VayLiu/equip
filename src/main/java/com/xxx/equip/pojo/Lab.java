package com.xxx.equip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lab")
public class Lab {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 实验室名称
     */
    @NotEmpty(message = "名称不能为空")
    private String name;
    /**
     * 实验室状态
     * 0:可用
     * 1:被申请
     */
    private int state;
    /**
     * 实验室简介
     */
    private String brief;
    /**
     * 负责人ID
     */
    private Integer managerId;
    /**
     * 负责教师ID
     */
    private Integer teacherId;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

}
