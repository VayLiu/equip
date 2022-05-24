package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.dto.EquipScrapDTO;
import com.xxx.equip.mapper.EquipRepairMapper;
import com.xxx.equip.mapper.EquipScrapMapper;
import com.xxx.equip.pojo.EquipRepair;
import com.xxx.equip.pojo.EquipScrap;
import com.xxx.equip.service.EquipRepairService;
import com.xxx.equip.service.EquipScrapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 设备业务层实现类
 */
@Service
@Transactional
public class EquipScrapServiceImpl extends ServiceImpl<EquipScrapMapper, EquipScrap> implements EquipScrapService {

    @Override
    public IPage<EquipScrapDTO> getPageByParam(Page<EquipScrap> page, EquipScrapDTO equipScrapDTO) {
        QueryWrapper<EquipScrap> queryWrapper = new QueryWrapper<>();

        if (null != equipScrapDTO.getLoginUserId() && 3 == equipScrapDTO.getLoginUserType()) {
            queryWrapper.eq("es.approve_id", equipScrapDTO.getLoginUserId());
        }
        if (null != equipScrapDTO.getLoginUserId() && 2 == equipScrapDTO.getLoginUserType()) {
            queryWrapper.eq("es.apply_id", equipScrapDTO.getLoginUserId());
        }
        if (null != equipScrapDTO.getState()) {
            queryWrapper.eq("es.state", equipScrapDTO.getState());
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public boolean updateStateBatchByIds(List<Integer> ids, Integer state) {
        List<EquipScrap> labApproveList = baseMapper.selectBatchIds(ids);
        Date date = new Date();
        for (EquipScrap equipScrap : labApproveList) {
            equipScrap.setState(state);
            equipScrap.setUpdatedAt(date);
        }
        return updateBatchById(labApproveList);
    }

}
