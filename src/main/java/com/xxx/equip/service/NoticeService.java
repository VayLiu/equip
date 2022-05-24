package com.xxx.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.pojo.User;

import java.util.List;

/**
 * @Description: 公告业务层
 */
public interface NoticeService extends IService<Notice> {

    IPage<NoticeDTO> getPageByParam(Page<NoticeDTO> page, Notice notice);

    List<NoticeDTO> listPublish(Notice notice);
}
