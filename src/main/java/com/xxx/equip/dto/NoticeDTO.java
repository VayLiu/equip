package com.xxx.equip.dto;

import com.xxx.equip.pojo.Notice;
import lombok.Data;

@Data
public class NoticeDTO extends Notice {

    /**
     * 发布者
     */
    private String publishName;
}
