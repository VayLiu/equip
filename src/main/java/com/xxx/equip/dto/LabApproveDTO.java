package com.xxx.equip.dto;

import com.xxx.equip.pojo.LabApprove;
import lombok.Data;

@Data
public class LabApproveDTO extends LabApprove {

    /**
     * 所属教师姓名
     */
    private String teacherName;
    /**
     * 申请学生姓名
     */
    private String studentName;
    /**
     * 申请实验室名称
     */
    private String labName;

}
