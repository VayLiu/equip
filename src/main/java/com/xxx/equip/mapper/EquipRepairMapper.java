package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.pojo.EquipRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备持久化层
 */
@Repository
public interface EquipRepairMapper extends BaseMapper<EquipRepair> {

    IPage<EquipRepairDTO> selectDtoPage(IPage<EquipRepair> page, @Param("ew") Wrapper<EquipRepair> queryWrapper);

}
