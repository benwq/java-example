package com.wqb.codegenerator.sensor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.wqb.codegenerator.sensor.entity.Water;
import com.wqb.codegenerator.sensor.service.IWaterService;


import org.springframework.web.bind.annotation.RestController;
import com.tangguangdi.base.BaseController;

/**
 * <p>
    *  前端控制器
    * </p>
 *
 * @author benwq
 * @since 2019-03-22
 */
@RestController
@RequestMapping("/sensor/water")
@Api(description = "")
public class WaterController extends BaseController {

    @Autowired
    private IWaterService waterService;

    /**
     * 列表
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "列表", notes = "列表")
    public Object list() {
        QueryWrapper<Water> wrapper = new QueryWrapper<>();
        return renderSuccess(waterService.list(wrapper));
    }

     /**
     * 分页
     */
    @PostMapping(value = "/page")
    @ApiOperation(value = "分页", notes = "分页")
    public Object page(Page<Water> page) {
        QueryWrapper<Water> wrapper = new QueryWrapper<>();
        return renderSuccess(waterService.page(page, wrapper));
    }

    /**
     * 详情
     */
    @GetMapping(value = "/getById/{id}")
    @ApiOperation(value = "详情", notes = "详情")
    public Object getById(@PathVariable("id") Long id) {
        return renderSuccess(waterService.getById(id));
    }

     /**
     * 新增
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增", notes = "新增")
    public Object save(Water water) {
        return renderSuccess(waterService.save(water));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/removeById")
    @ApiOperation(value = "删除", notes = "删除")
    public Object removeById(@RequestParam Long id) {
        return renderSuccess(waterService.removeById(id));
    }

    /**
     * 修改
     */
    @PostMapping(value = "/updateById")
    @ApiOperation(value = "修改", notes = "修改")
    public Object updateById(Water water) {
        return renderSuccess(waterService.updateById(water));
    }
}
