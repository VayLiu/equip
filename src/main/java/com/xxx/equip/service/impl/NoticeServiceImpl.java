package com.xxx.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.mapper.NoticeMapper;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.pojo.User;
import com.xxx.equip.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 公告业务层实现类
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public IPage<NoticeDTO> getPageByParam(Page<NoticeDTO> page, Notice notice) {
        QueryWrapper<NoticeDTO> queryWrapper = new QueryWrapper<>();
        if (null != notice.getIsCarousel()) {
            queryWrapper.eq("new.is_carousel", notice.getIsCarousel());
        }
        if (null != notice.getPublishId()) {
            queryWrapper.eq("new.publish_id", notice.getPublishId());
        }
        if (null != notice.getPublishType()) {
            queryWrapper.eq("new.publish_type", notice.getPublishType());
        }
        if (!StringUtils.isEmpty(notice.getTitle())) {
            queryWrapper.like("new.title", notice.getTitle());
        }
        queryWrapper.orderByDesc("updated_at");

        return baseMapper.selectDtoPage(page, queryWrapper);
    }

    @Override
    public List<NoticeDTO> listPublish(Notice notice) {
        QueryWrapper<NoticeDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("new.publish_type", notice.getPublishType());
        if (null == notice.getPublishType()) {
            return new ArrayList<NoticeDTO>();
        }
        return baseMapper.listPublish(queryWrapper);
    }
}
