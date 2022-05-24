package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.LabDTO;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.mapper.LabMapper;
import com.xxx.equip.pojo.EquipScrap;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.service.LabService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 实验室业务层实现类
 */
@Service
@Transactional
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab> implements LabService {

    @Override
    public IPage<LabDTO> getPageByParam(Page<LabDTO> page, Lab lab) {
        QueryWrapper<LabDTO> queryWrapper = new QueryWrapper<>();
        if (null != lab.getManagerId()) {
            queryWrapper.eq("l.manager_id", lab.getManagerId());
        }
        if (null != lab.getTeacherId()) {
            queryWrapper.eq("l.teacher_id", lab.getTeacherId());
        }
        if (!StringUtils.isEmpty(lab.getName())) {
            queryWrapper.like("l.name", lab.getName());
        }
        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public Lab getByTeacherId(Integer id) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Lab> listLab(LabDTO labDTO) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        if (null != labDTO.getLoginUserId() && 3 == labDTO.getLoginUserType()) {
            queryWrapper.eq("manager_id", labDTO.getLoginUserId());
        }
        if (null != labDTO.getLoginUserId() && 2 == labDTO.getLoginUserType()) {
            queryWrapper.eq("teacher_id", labDTO.getLoginUserId());
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<LabDTO> listTeacher(Lab lab) {
        QueryWrapper<LabDTO> queryWrapper = new QueryWrapper<>();
        if (null != lab.getManagerId()) {
            queryWrapper.eq("l.manager_id", lab.getManagerId());
        }
        return baseMapper.listTeacher(queryWrapper);
    }


}
