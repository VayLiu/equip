package com.xxx.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.EquipSubDTO;
import com.xxx.equip.pojo.EquipSub;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.service.EquipSubService;
import com.xxx.equip.service.EquipService;
import com.xxx.equip.service.LabService;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "设备申购控制")
@RestController
@RequestMapping("/equip/sub")
public class EquipSubController {

    @Autowired
    private EquipService equipService;

    @Autowired
    private LabService labService;

    @Autowired
    private EquipSubService equipSubService;

    @ApiOperation("设备申购申请")
    @PostMapping("/apply")
    public Result apply(@RequestBody EquipSubDTO equipSubDTO) {

        if (null != equipSubDTO.getLoginUserType() && equipSubDTO.getLoginUserType() == 3) {
            equipSubDTO.setState(1);
        }

        if (null == equipSubDTO.getApproveId()) {
            Lab lab = labService.getById(equipSubDTO.getLabId());
            equipSubDTO.setApproveId(lab.getManagerId());
        }
        Date date = new Date();
        if (null == equipSubDTO.getId()) {
            equipSubDTO.setCreatedAt(date);
            equipSubDTO.setUpdatedAt(date);
        } else {
            equipSubDTO.setUpdatedAt(date);
        }
        equipSubService.saveOrUpdate(equipSubDTO);

        if (null != equipSubDTO.getLoginUserType() && equipSubDTO.getLoginUserType() == 3) {
            QueryWrapper<EquipSub> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("equip_name", equipSubDTO.getEquipName());
            queryWrapper.like("equip_quantity", equipSubDTO.getEquipQuantity());
            queryWrapper.eq("lab_id", equipSubDTO.getLabId());
            queryWrapper.eq("sub_id", equipSubDTO.getSubId());
            EquipSub one = equipSubService.getOne(queryWrapper);
            List<Integer> ids = new ArrayList<>();
            ids.add(one.getId());
            equipSubService.intoLab(ids);
        }
        return Result.success();
    }


    @ApiOperation("设备维修审批查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getRepairAll(@PathVariable Integer pageNumber,
                               @PathVariable Integer pageSize,
                               EquipSubDTO EquipSubDTO) {
        Page<EquipSub> page = new Page<>(pageNumber, pageSize);
        IPage<EquipSubDTO> iPage = equipSubService.getPageByParam(page, EquipSubDTO);
        return Result.success(iPage);
    }

    @ApiOperation("批量同意")
    @PostMapping("/approve/agree")
    public Result agree(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean a = equipSubService.updateStateBatchByIds(ids, 1);
        boolean b = equipSubService.intoLab(ids);
        if (a && b) return Result.success();
        else return Result.fail();
    }

    @ApiOperation("批量不同意")
    @PostMapping("/approve/disagree")
    public Result disagree(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = equipSubService.updateStateBatchByIds(ids, 2);
        if (b) return Result.success();
        else return Result.fail();
    }

}
