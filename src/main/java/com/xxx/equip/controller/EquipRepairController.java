package com.xxx.equip.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.EquipRepairDTO;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.pojo.EquipRepair;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.service.EquipRepairService;
import com.xxx.equip.service.EquipService;
import com.xxx.equip.service.LabService;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "设备维修控制")
@RestController
@RequestMapping("/equip/repair")
public class EquipRepairController {

    @Autowired
    private EquipService equipService;

    @Autowired
    private LabService labService;

    @Autowired
    private EquipRepairService equipRepairService;

    @ApiOperation("设备维修")
    @PostMapping("/apply")
    public Result repair(@RequestBody EquipRepairDTO equipRepairDTO) {

        if (null != equipRepairDTO.getLoginUserType() && equipRepairDTO.getLoginUserType() == 3) {
            equipRepairDTO.setState(1);
        }
        if (null == equipRepairDTO.getApproveId()) {
            Lab lab = labService.getById(equipRepairDTO.getLabId());
            equipRepairDTO.setApproveId(lab.getManagerId());
        }
        Date date = new Date();
        if (null == equipRepairDTO.getId()) {
            equipRepairDTO.setCreatedAt(date);
            equipRepairDTO.setUpdatedAt(date);
        } else {
            equipRepairDTO.setUpdatedAt(date);
        }
        Equip equip = equipService.getById(equipRepairDTO.getEquipId());
        equip.setQuantity(equip.getQuantity() - equipRepairDTO.getQuantity());
        equipService.saveOrUpdate(equip);
        equipRepairService.saveOrUpdate(equipRepairDTO);
        return Result.success();
    }


    @ApiOperation("设备维修审批查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getRepairAll(@PathVariable Integer pageNumber,
                               @PathVariable Integer pageSize,
                               EquipRepairDTO equipRepairDTO) {
        Page<EquipRepair> page = new Page<>(pageNumber, pageSize);
        IPage<EquipRepairDTO> iPage = equipRepairService.getPageByParam(page, equipRepairDTO);
        return Result.success(iPage);
    }

    @ApiOperation("批量同意")
    @PostMapping("/approve/agree")
    public Result agree(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = equipRepairService.updateStateBatchByIds(ids, 1);
        if (b) return Result.success();
        else return Result.fail();
    }

    @ApiOperation("批量不同意")
    @PostMapping("/approve/disagree")
    public Result disagree(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = equipRepairService.updateStateBatchByIds(ids, 2);
        if (b) return Result.success();
        else return Result.fail();
    }

    @ApiOperation("维修成功")
    @PostMapping("/success")
    public Result success(
            @RequestBody EquipRepair equipRepair
    ) {
        Equip equip = equipService.getById(equipRepair.getEquipId());
        equip.setQuantity(equip.getQuantity() + equipRepair.getQuantity());
        equipRepair.setState(3);
        equipService.saveOrUpdate(equip);
        equipRepairService.saveOrUpdate(equipRepair);
        return Result.success();
    }

    @ApiOperation("维修失败")
    @PostMapping("/fail")
    public Result fail(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = equipRepairService.updateStateBatchByIds(ids, 4);
        if (b) return Result.success();
        else return Result.fail();
    }
}
