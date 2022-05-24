package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.pojo.User;
import com.xxx.equip.service.NoticeService;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "公告控制")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation("添加及修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            Map<String, Object> map = new LinkedHashMap<>();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Result.fail(map);
        } else {
            // 轮播上限判断
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_carousel", notice.getIsCarousel());
            int count = noticeService.count(queryWrapper);
            if (count >= 5)
                return Result.fail().message("轮播数超过上限，请联系系统管理员设置！");

            Date date = new Date();
            if (null == notice.getId()) {
                notice.setCreatedAt(date);
                notice.setUpdatedAt(date);
            } else {
                notice.setUpdatedAt(date);
            }

            noticeService.saveOrUpdate(notice);
            return Result.success();
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids) {
        noticeService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("根据id查询个体")
    @GetMapping("/getOne/{id}")
    public Result getOne(@PathVariable Integer id) {
        Notice notice = noticeService.getById(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("notice", notice);
        return Result.success(map);
    }

    @ApiOperation("分页模糊查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getAll(@PathVariable Integer pageNumber,
                         @PathVariable Integer pageSize,
                         Notice notice) {
        Page<NoticeDTO> page = new Page<>(pageNumber, pageSize);
        IPage<NoticeDTO> iPage = noticeService.getPageByParam(page, notice);
        return Result.success(iPage);
    }

    @ApiOperation("获取轮播公告")
    @GetMapping("/getCarouselNotice")
    public Result getCarouselNotice() {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_carousel", 1);
        List<Notice> list = noticeService.list(queryWrapper);
        return Result.success(list);
    }

    @ApiOperation("获取所有发布者")
    @GetMapping("/getAllPublish")
    public Result getAllPublish(Notice notice) {
        List<NoticeDTO> list = noticeService.listPublish(notice);
        return Result.success(list);
    }

    @ApiOperation("统计公告数")
    @GetMapping("/getCount")
    public Result getCount(Notice notice) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        if (null != notice.getPublishId()) {
            queryWrapper.eq("publish_id", notice.getPublishId());
        }
        Integer num = noticeService.count(queryWrapper);
        return Result.success(num);
    }

}
