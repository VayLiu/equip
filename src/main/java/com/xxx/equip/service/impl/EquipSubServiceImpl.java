package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.EquipSubDTO;
import com.xxx.equip.mapper.EquipMapper;
import com.xxx.equip.mapper.EquipSubMapper;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.pojo.EquipSub;
import com.xxx.equip.service.EquipSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 设备业务层实现类
 */
@Service
@Transactional
public class EquipSubServiceImpl extends ServiceImpl<EquipSubMapper, EquipSub> implements EquipSubService {

    @Autowired
    EquipMapper equipMapper;

    @Override
    public IPage<EquipSubDTO> getPageByParam(Page<EquipSub> page, EquipSubDTO equipSubDTO) {
        QueryWrapper<EquipSub> queryWrapper = new QueryWrapper<>();

        if (null != equipSubDTO.getLoginUserId() && 3 == equipSubDTO.getLoginUserType()) {
            queryWrapper.eq("es.approve_id", equipSubDTO.getLoginUserId());
        }

        if (null != equipSubDTO.getLoginUserId() && 2 == equipSubDTO.getLoginUserType()) {
            queryWrapper.eq("es.sub_id", equipSubDTO.getLoginUserId());
        }

        if (null != equipSubDTO.getState()) {
            queryWrapper.eq("es.state", equipSubDTO.getState());
        }

        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public boolean updateStateBatchByIds(List<Integer> ids, Integer state) {
        List<EquipSub> equipSubList = baseMapper.selectBatchIds(ids);
        Date date = new Date();
        for (EquipSub equipSub : equipSubList) {
            equipSub.setState(state);
            equipSub.setUpdatedAt(date);
        }
        return updateBatchById(equipSubList);
    }

    @Override
    public boolean intoLab(List<Integer> ids) {
        List<EquipSub> equipSubList = baseMapper.selectBatchIds(ids);
        for (EquipSub equipSub : equipSubList) {
            Date date = new Date();
            Equip equip = new Equip();
            equip.setName(equipSub.getEquipName());
            equip.setQuantity(equipSub.getEquipQuantity());
            equip.setPrice(equipSub.getEquipPrice());
            equip.setDatePro(equipSub.getEquipDatePro());
            equip.setOrigin(equipSub.getEquipOrigin());
            equip.setLabId(equipSub.getLabId());
            equip.setSubId(equipSub.getSubId());
            equip.setCreatedAt(date);
            equip.setUpdatedAt(date);
            equipMapper.insert(equip);
        }
        return true;
    }

}
