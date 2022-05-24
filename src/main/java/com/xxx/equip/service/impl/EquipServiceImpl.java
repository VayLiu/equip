package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.EquipDTO;
import com.xxx.equip.mapper.EquipMapper;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.service.EquipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 设备业务层实现类
 */
@Service
@Transactional
public class EquipServiceImpl extends ServiceImpl<EquipMapper, Equip> implements EquipService {

    @Override
    public IPage<EquipDTO> getPageByParam(Page<Equip> page, EquipDTO equipDTO) {
        QueryWrapper<Equip> queryWrapper = new QueryWrapper<>();
        if (null != equipDTO.getLoginLabId() && 2 == equipDTO.getLoginUserType()) {
            queryWrapper.eq("e.lab_id", equipDTO.getLoginLabId());
        }
        if (null != equipDTO.getLoginUserId() && 3 == equipDTO.getLoginUserType()) {
            queryWrapper.eq("l.manager_id", equipDTO.getLoginUserId());
        }
        if (null != equipDTO.getName()) {
            queryWrapper.like("e.name", equipDTO.getName());
        }
        queryWrapper.ne("e.quantity", 0);
        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public Integer countEquip(Equip equip) {
        QueryWrapper<Equip> queryWrapper = new QueryWrapper<>();
        if (null != equip.getLabId()) {
            queryWrapper.eq("lab_id", equip.getLabId());
        }
        List<Equip> equips = baseMapper.selectList(queryWrapper);
        int num = 0;
        for (Equip e : equips) {
            num += e.getQuantity();
        }
        return num;
    }

}
