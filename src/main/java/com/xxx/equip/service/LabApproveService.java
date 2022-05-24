package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.LabApproveDTO;
import com.xxx.equip.pojo.LabApprove;

import java.util.List;

/**
 * @Description: 实验室业务层
 */
public interface LabApproveService extends IService<LabApprove> {

    IPage<LabApproveDTO> getPageByParam(Page<LabApproveDTO> page, LabApprove labApprove);

    /**
     * 批量设置审批状态
     * @param ids 审批id
     * @param state 0：未审批 1：通过 2：未通过
     * @return 操作是否成功
     */
    boolean updateStateBatchByIds(List<Integer> ids, Integer state);

    /**
     * 设置审批状态为通过
     * @param id 审批id
     * @return 操作是否成功
     * -1:被占用
     */
    int agree(Integer id);

    int apply(LabApprove labApprove);
}
