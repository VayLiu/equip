package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.EquipScrapDTO;
import com.xxx.equip.pojo.EquipScrap;

import java.util.List;

/**
 * @Description: 设备业务层
 */
public interface EquipScrapService extends IService<EquipScrap> {

    IPage<EquipScrapDTO> getPageByParam(Page<EquipScrap> page, EquipScrapDTO EquipScrapDTO);

    boolean updateStateBatchByIds(List<Integer> ids, Integer state);
}
