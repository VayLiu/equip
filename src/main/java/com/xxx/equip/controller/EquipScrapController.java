package com.xxx.equip.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.EquipScrapDTO;
import com.xxx.equip.pojo.Equip;
import com.xxx.equip.pojo.EquipScrap;
import com.xxx.equip.pojo.Lab;
import com.xxx.equip.service.EquipScrapService;
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

@Api(tags = "设备报废控制")
@RestController
@RequestMapping("/equip/scrap")
public class EquipScrapController {

    @Autowired
    private EquipService equipService;

    @Autowired
    private LabService labService;

    @Autowired
    private EquipScrapService equipScrapService;

    @ApiOperation("设备报废")
    @PostMapping("/apply")
    public Result repair(@RequestBody EquipScrapDTO equipScrapDTO) {

        if (null != equipScrapDTO.getLoginUserType() && equipScrapDTO.getLoginUserType() == 3) {
            equipScrapDTO.setState(1);
        }
        if (null == equipScrapDTO.getApproveId()) {
            Lab lab = labService.getById(equipScrapDTO.getLabId());
            equipScrapDTO.setApproveId(lab.getManagerId());
        }
        Date date = new Date();
        if (null == equipScrapDTO.getId()) {
            equipScrapDTO.setCreatedAt(date);
            equipScrapDTO.setUpdatedAt(date);
        } else {
            equipScrapDTO.setUpdatedAt(date);
        }
        Equip equip = equipService.getById(equipScrapDTO.getEquipId());
        equip.setQuantity(equip.getQuantity() - equipScrapDTO.getQuantity());
        equipService.saveOrUpdate(equip);
        equipScrapService.saveOrUpdate(equipScrapDTO);
        return Result.success();
    }


    @ApiOperation("设备报废审批查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getRepairAll(@PathVariable Integer pageNumber,
                               @PathVariable Integer pageSize,
                               EquipScrapDTO equipScrapDTO) {
        Page<EquipScrap> page = new Page<>(pageNumber, pageSize);
        IPage<EquipScrapDTO> iPage = equipScrapService.getPageByParam(page, equipScrapDTO);
        return Result.success(iPage);
    }

    @ApiOperation("报废成功")
    @PostMapping("/success")
    public Result success(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = equipScrapService.updateStateBatchByIds(ids, 1);
        if (b) return Result.success();
        else return Result.fail();
    }

    @ApiOperation("报废失败")
    @PostMapping("/fail")
    public Result fail(
            @RequestBody EquipScrap equipScrap
    ) {
        Equip equip = equipService.getById(equipScrap.getEquipId());
        equip.setQuantity(equip.getQuantity() + equipScrap.getQuantity());
        equipScrap.setState(2);
        equipService.saveOrUpdate(equip);
        equipScrapService.saveOrUpdate(equipScrap);
        return Result.success();
    }
}
