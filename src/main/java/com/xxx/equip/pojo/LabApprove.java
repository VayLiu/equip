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
@TableName("lab_approve")
public class LabApprove {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 实验室id
     */
    private Integer labId;
    /**
     * 负责教师ID
     */
    private Integer teacherId;
    /**
     * 申请学生ID
     */
    private Integer studentId;
    /**
     * 申请理由
     */
    private String reason;
    /**
     * 审批状态
     * 0:未审批
     * 1:审批通过
     * 2:审批未通过
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