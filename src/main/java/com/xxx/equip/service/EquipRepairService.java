package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.EquipDTO;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.pojo.EquipRepair;

import java.util.List;

/**
 * @Description: 设备业务层
 */
public interface EquipRepairService extends IService<EquipRepair> {

    IPage<EquipRepairDTO> getPageByParam(Page<EquipRepair> page, EquipRepairDTO equipRepairDTO);

    boolean updateStateBatchByIds(List<Integer> ids, Integer state);
}
