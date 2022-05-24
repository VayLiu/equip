package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.EquipDTO;
import com.xxx.equip.pojo.Equip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备持久化层
 */
@Repository
public interface EquipMapper extends BaseMapper<Equip> {

    IPage<EquipDTO> selectDtoPage(IPage<Equip> page, @Param("ew") Wrapper<Equip> queryWrapper);

}
