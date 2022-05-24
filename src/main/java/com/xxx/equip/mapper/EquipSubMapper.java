package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.EquipSubDTO;
import com.xxx.equip.pojo.EquipSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备持久化层
 */
@Repository
public interface EquipSubMapper extends BaseMapper<EquipSub> {

    IPage<EquipSubDTO> selectDtoPage(IPage<EquipSub> page, @Param("ew") Wrapper<EquipSub> queryWrapper);

}
