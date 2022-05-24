package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.LabApproveDTO;
import com.xxx.equip.mapper.LabApproveMapper;
import com.xxx.equip.mapper.LabMapper;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.LabApprove;
import com.xxx.equip.service.LabApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description: 实验室审批业务层实现类
 */
@Service
@Transactional
public class LabApproveServiceImpl extends ServiceImpl<LabApproveMapper, LabApprove> implements LabApproveService {

    @Autowired
    private LabMapper labMapper;

    @Override
    public IPage<LabApproveDTO> getPageByParam(Page<LabApproveDTO> page, LabApprove LabApprove) {
        QueryWrapper<LabApproveDTO> queryWrapper = new QueryWrapper<>();
        if (null != LabApprove.getTeacherId()) {
            queryWrapper.eq("l.teacher_id", LabApprove.getTeacherId());
        }
        if (null != LabApprove.getStudentId()) {
            queryWrapper.eq("l.student_id", LabApprove.getStudentId());
        }
        if (null != LabApprove.getState()) {
            queryWrapper.eq("l.state", LabApprove.getState());
        }
        queryWrapper.orderByAsc("id");
        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public boolean updateStateBatchByIds(List<Integer> ids, Integer state) {
        List<LabApprove> labApproveList = baseMapper.selectBatchIds(ids);
        Date date = new Date();
        for (LabApprove labApprove : labApproveList) {
            labApprove.setState(state);
            labApprove.setUpdatedAt(date);
        }
        return updateBatchById(labApproveList);
    }

    @Override
    public int agree(Integer id) {
        LabApprove labApprove = baseMapper.selectById(id);
        Lab lab = labMapper.selectById(labApprove.getLabId());
        if (1 == lab.getState()) {
            return -1;
        } else {
            labApprove.setState(1);
            labApprove.setUpdatedAt(new Date());
            return baseMapper.updateById(labApprove);
        }
    }

    @Override
    public int apply(LabApprove labApprove) {
        labApprove.setState(0);
        Date date = new Date();
        labApprove.setCreatedAt(date);
        labApprove.setUpdatedAt(date);
        return baseMapper.insert(labApprove);
    }
}
