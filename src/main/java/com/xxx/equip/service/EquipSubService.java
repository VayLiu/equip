package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.EquipSubDTO;
import com.xxx.equip.pojo.EquipSub;

import java.util.List;

/**
 * @Description: 设备业务层
 */
public interface EquipSubService extends IService<EquipSub> {

    IPage<EquipSubDTO> getPageByParam(Page<EquipSub> page, EquipSubDTO equipSubDTO);

    boolean updateStateBatchByIds(List<Integer> ids, Integer state);

    boolean intoLab(List<Integer> ids);
}
