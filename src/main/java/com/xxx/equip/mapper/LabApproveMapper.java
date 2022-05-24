package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.LabApproveDTO;
import com.xxx.equip.pojo.LabApprove;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LabApproveMapper extends BaseMapper<LabApprove> {

    IPage<LabApproveDTO> selectDtoPage(IPage<LabApproveDTO> page, @Param("ew") Wrapper<LabApproveDTO> queryWrapper);

}
