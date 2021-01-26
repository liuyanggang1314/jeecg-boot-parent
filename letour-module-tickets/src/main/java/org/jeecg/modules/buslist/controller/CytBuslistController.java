package org.jeecg.modules.buslist.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.buslist.entity.CytBuslist;
import org.jeecg.modules.buslist.service.ICytBuslistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 班次列表
 * @Author: 刘杨刚
 * @Date: 2021-01-11
 * @Version: V1.0
 */
@Api(tags = "班次列表")
@RestController
@RequestMapping("/buslist/cytBuslist")
@Slf4j
public class CytBuslistController extends JeecgController<CytBuslist, ICytBuslistService> {
    @Autowired
    private ICytBuslistService cytBuslistService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param cytBuslist
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "班次列表-分页列表查询")
    @ApiOperation(value = "班次列表-分页列表查询", notes = "班次列表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CytBuslist cytBuslist,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CytBuslist> queryWrapper = QueryGenerator.initQueryWrapper(cytBuslist, req.getParameterMap());
        Page<CytBuslist> page = new Page<CytBuslist>(pageNo, pageSize);
        IPage<CytBuslist> pageList = cytBuslistService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cytBuslist
     * @return
     */
    @AutoLog(value = "班次列表-添加")
    @ApiOperation(value = "班次列表-添加", notes = "班次列表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CytBuslist cytBuslist) {
        cytBuslistService.save(cytBuslist);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cytBuslist
     * @return
     */
    @AutoLog(value = "班次列表-编辑")
    @ApiOperation(value = "班次列表-编辑", notes = "班次列表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CytBuslist cytBuslist) {
        cytBuslistService.updateById(cytBuslist);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "班次列表-通过id删除")
    @ApiOperation(value = "班次列表-通过id删除", notes = "班次列表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cytBuslistService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "班次列表-批量删除")
    @ApiOperation(value = "班次列表-批量删除", notes = "班次列表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cytBuslistService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "班次列表-通过id查询")
    @ApiOperation(value = "班次列表-通过id查询", notes = "班次列表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CytBuslist cytBuslist = cytBuslistService.getById(id);
        if (cytBuslist == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cytBuslist);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cytBuslist
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CytBuslist cytBuslist) {
        return super.exportXls(request, cytBuslist, CytBuslist.class, "班次列表");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CytBuslist.class);
    }

    /**
     * 更新班次列表数据--盛威
     *
     * @return
     */
    @AutoLog(value = "班次列表-更新班次列表数据")
    @ApiOperation(value = "班次列表-更新班次列表数据", notes = "班次列表-更新班次列表数据")
    @GetMapping(value = "/updateBusList")
    public Result<?> updateBusList(@RequestParam String departDate) {
        return cytBuslistService.updateBusList(departDate);
    }

    /**
     * 获取班次列表数据
     *
     * @return
     */
    @AutoLog(value = "到达站-获取班次列表数据")
    @ApiOperation(value = "到达站-获取班次列表数据", notes = "到达站-获取班次列表数据")
    @GetMapping(value = "/getBusList")
    public Result<?> getBusList(@RequestParam String departcityId, @RequestParam String endStationName, @RequestParam String departDate) {
       return cytBuslistService.getBusList(departcityId,endStationName,departDate);
    }
}
