package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.mapper.EquipRepairMapper;
import com.xxx.equip.pojo.EquipRepair;
import com.xxx.equip.service.EquipRepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 设备业务层实现类
 */
@Service
@Transactional
public class EquipRepairServiceImpl extends ServiceImpl<EquipRepairMapper, EquipRepair> implements EquipRepairService {

    @Override
    public IPage<EquipRepairDTO> getPageByParam(Page<EquipRepair> page, EquipRepairDTO equipRepairDTO) {
        QueryWrapper<EquipRepair> queryWrapper = new QueryWrapper<>();

        if (null != equipRepairDTO.getLoginUserId() && 3 == equipRepairDTO.getLoginUserType()) {
            queryWrapper.eq("er.approve_id", equipRepairDTO.getLoginUserId());
        }
        if (null != equipRepairDTO.getLoginUserId() && 2 == equipRepairDTO.getLoginUserType()) {
            queryWrapper.eq("er.apply_id", equipRepairDTO.getLoginUserId());
        }
        if (null != equipRepairDTO.getState()) {
            queryWrapper.eq("er.state", equipRepairDTO.getState());
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public boolean updateStateBatchByIds(List<Integer> ids, Integer state) {
        List<EquipRepair> labApproveList = baseMapper.selectBatchIds(ids);
        Date date = new Date();
        for (EquipRepair equipRepair : labApproveList) {
            equipRepair.setState(state);
            equipRepair.setUpdatedAt(date);
        }
        return updateBatchById(labApproveList);
    }

}
