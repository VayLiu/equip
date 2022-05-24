package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.equip.dto.LabApproveDTO;
import com.xxx.equip.pojo.LabApprove;
import com.xxx.equip.pojo.Login;
import com.xxx.equip.service.LabApproveService;
import com.xxx.equip.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "实验室审批")
@RestController
@RequestMapping("/lab/approve")
public class LabApproveController {

    @Autowired
    private LabApproveService labApproveService;

    @ApiOperation("实验室申请")
    @PostMapping("/apply")
    public Result apply(
            @ApiParam("要新增的JSON的对象") @RequestBody LabApprove labApprove
    ) {
        labApproveService.apply(labApprove);
        return Result.success();
    }

    @ApiOperation("批量同意")
    @PostMapping("/agree")
    public Result agree(
            @ApiParam("关于id的JSON集合") @RequestBody Integer id
    ) {

        int agree = labApproveService.agree(id);
        if (agree == -1) return Result.fail().message("您的实验室已被占用，请重新开放！");
        else return Result.success();
    }

    @ApiOperation("批量不同意")
    @PostMapping("/disagree")
    public Result disagree(
            @ApiParam("关于id的JSON集合") @RequestBody List<Integer> ids
    ) {
        boolean b = labApproveService.updateStateBatchByIds(ids, 2);
        if (b) return Result.success();
        else return Result.fail();
    }

    @ApiOperation("审批模糊分页查询")
    @GetMapping("/getAll/{pageNumber}/{pageSize}")
    public Result getAll(
            @ApiParam("分页查询的页码") @PathVariable Integer pageNumber,
            @ApiParam("分页查询的页大小") @PathVariable Integer pageSize,
            @ApiParam("分页查询模糊匹配的属性") LabApprove labApprove
    ) {
        Page<LabApproveDTO> page = new Page<>(pageNumber, pageSize);
        IPage<LabApproveDTO> iPage = labApproveService.getPageByParam(page, labApprove);
        return Result.success(iPage);
    }


    @ApiOperation("统计实验室申请数")
    @GetMapping("/getCount")
    public Result getCount(LabApprove labApprove) {
        long oneMonth = 30L * 24 * 60 * 60 * 1000;
        Date date = new Date();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            QueryWrapper<LabApprove> queryWrapper = new QueryWrapper<>();
            if (null != labApprove.getLabId()) {
                queryWrapper.eq("lab_id", labApprove.getLabId());
            }
            queryWrapper.eq("state", 1);
            queryWrapper.between("updated_at", new Date(date.getTime() - oneMonth * i), new Date(date.getTime() - oneMonth * (i - 1)));
            HashMap<String, Object> map = new HashMap<>();
            map.put("date", new Date(date.getTime() - oneMonth * (i - 1)));
            map.put("num", labApproveService.count(queryWrapper));
            list.add(map);
        }
        return Result.success(list);
    }

}
