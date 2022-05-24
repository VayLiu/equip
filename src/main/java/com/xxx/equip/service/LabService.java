package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.LabDTO;
import com.xxx.equip.pojo.Lab;

import java.util.List;

/**
 * @Description: 实验室业务层
 */
public interface LabService extends IService<Lab> {

    IPage<LabDTO> getPageByParam(Page<LabDTO> page, Lab lab);

    Lab getByTeacherId(Integer id);

    List<Lab> listLab(LabDTO labDTO);

    List<LabDTO> listTeacher(Lab lab);
}
