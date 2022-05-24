package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.LabDTO;
import com.xxx.equip.dto.NoticeDTO;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.pojo.User;
import com.xxx.equip.service.LabService;
import com.xxx.equip.util.MD5;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "实验室控制")
@RestController
@RequestMapping("/lab")
public class LabController {

    @Autowired
    private LabService labService;

    @ApiOperation("新增或修改，有ID属性是修改，没有则是增加")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(
            @ApiParam("要新增或修改的JSON的对象") @RequestBody Lab lab,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            Map<String, Object> map = new LinkedHashMap<>();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Result.fail(map);
        } else {
            Date date = new Date();
            if (null == lab.getId()) {
                lab.setCreatedAt(date);
                lab.setUpdatedAt(date);
            } else {
                lab.setUpdatedAt(date);
            }
            labService.saveOrUpdate(lab);
            return Result.success();
        }
    }

    @ApiOperation("批量删除信息")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam("要删除的所有id的JSON集合") @RequestBody List<Integer> ids
    ) {
        labService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("根据id查询个体")
    @GetMapping("/getOne/{id}")
    public Result getOne(
            @ApiParam("id") @PathVariable Integer id
    ) {
        Lab lab = labService.getById(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("lab", lab);
        return Result.success(map);
    }

    @ApiOperation("根据负责教师id查询个体")
    @GetMapping("/getOneByTeacherId/{id}")
    public Result getOneByTeacherId(
            @ApiParam("id") @PathVariable Integer id
    ) {
        Lab lab = labService.getByTeacherId(id);
        if (lab != null) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("lab", lab);
            return Result.success(map);
        } else {
            return Result.fail();
        }
    }


    @ApiOperation("根据实验室信息模糊分页查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getAll(
            @ApiParam("分页查询的页码") @PathVariable Integer pageNumber,
            @ApiParam("分页查询的页大小") @PathVariable Integer pageSize,
            @ApiParam("分页查询模糊匹配的实验室属性") Lab lab
    ) {
        Page<LabDTO> page = new Page<>(pageNumber, pageSize);
        IPage<LabDTO> iPage = labService.getPageByParam(page, lab);
        return Result.success(iPage);
    }

    @ApiOperation("获取所有实验室")
    @GetMapping("/getAll")
    public Result getAllLab(LabDTO labDTO) {
        List<Lab> list = labService.listLab(labDTO);
        return Result.success(list);
    }

    @ApiOperation("获取教师")
    @GetMapping("/getAllTeacher")
    public Result getAllPublish(Lab lab) {
        List<LabDTO> list = labService.listTeacher(lab);
        return Result.success(list);
    }

    @ApiOperation("统计实验室数")
    @GetMapping("/getCount")
    public Result getCount(Lab la) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        if (null != la.getManagerId()) {
            queryWrapper.eq("manager_id", la.getManagerId());
        }
        Integer num = labService.count(queryWrapper);
        return Result.success(num);
    }

}
