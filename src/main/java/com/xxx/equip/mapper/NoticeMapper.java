package com.xxx.equip.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.pojo.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 公告持久化层
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    IPage<NoticeDTO> selectDtoPage(IPage<NoticeDTO> page, @Param("ew") Wrapper<NoticeDTO> queryWrapper);

    List<NoticeDTO> listPublish(@Param("ew") Wrapper<NoticeDTO> queryWrapper);
}
