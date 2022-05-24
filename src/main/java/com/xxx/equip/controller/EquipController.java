package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.EquipDTO;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.pojo.EquipRepair;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.pojo.Notice;
import com.xxx.equip.service.EquipRepairService;
import com.xxx.equip.service.EquipService;
import com.xxx.equip.service.LabService;
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

@Api(tags = "设备控制")
@RestController
@RequestMapping("/equip")
public class EquipController {

    @Autowired
    private EquipService equipService;

    @ApiOperation("添加及修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Equip equip, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            Map<String, Object> map = new LinkedHashMap<>();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Result.fail(map);
        } else {
            Date date = new Date();
            if (null == equip.getId()) {
                equip.setCreatedAt(date);
                equip.setUpdatedAt(date);
            } else {
                equip.setUpdatedAt(date);
            }
            equipService.saveOrUpdate(equip);
            return Result.success();
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids) {
        equipService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation("根据id查询个体")
    @GetMapping("/getOne/{id}")
    public Result getOne(@PathVariable Integer id) {
        Equip equip = equipService.getById(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("equip", equip);
        return Result.success(map);
    }

    @ApiOperation("分页模糊查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getAll(@PathVariable Integer pageNumber,
                         @PathVariable Integer pageSize,
                         EquipDTO equipDTO) {
        Page<Equip> page = new Page<>(pageNumber, pageSize);
        IPage<EquipDTO> iPage = equipService.getPageByParam(page, equipDTO);
        return Result.success(iPage);
    }

    @ApiOperation("统计设备数")
    @GetMapping("/getCount")
    public Result getCount(Equip equip) {
        Integer num = equipService.countEquip(equip);
        return Result.success(num);
    }

}
