package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.EquipDTO;
import com.xxx.equip.pojo.Equip;

/**
 * @Description: 设备业务层
 */
public interface EquipService extends IService<Equip> {

    IPage<EquipDTO> getPageByParam(Page<Equip> page, EquipDTO equipDTO);

    Integer countEquip(Equip equip);
}
