package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.LabDTO;
import com.xxx.equip.pojo.Lab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Description: 实验室持久化层
 */
@Repository
public interface LabMapper extends BaseMapper<Lab> {

    IPage<LabDTO> selectDtoPage(IPage<LabDTO> page, @Param("ew") Wrapper<LabDTO> queryWrapper);

    List<LabDTO> listTeacher(@Param("ew") QueryWrapper<LabDTO> queryWrapper);
}
