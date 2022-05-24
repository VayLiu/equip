package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.EquipScrapDTO;
import com.xxx.equip.pojo.EquipScrap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 设备持久化层
 */
@Repository
public interface EquipScrapMapper extends BaseMapper<EquipScrap> {

    IPage<EquipScrapDTO> selectDtoPage(IPage<EquipScrap> page, @Param("ew") Wrapper<EquipScrap> queryWrapper);

}
