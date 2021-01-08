package org.jeecg.modules.station.controller;

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
import org.jeecg.modules.station.entity.CytStartStation;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 出发站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Api(tags = "出发站")
@RestController
@RequestMapping("/station/cytStartStation")
@Slf4j
public class CytStartStationController extends JeecgController<CytStartStation, ICytStartStationService> {

    @Autowired
    private ICytStartStationService cytStartStationService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param cytStartStation
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "出发站-分页列表查询")
    @ApiOperation(value = "出发站-分页列表查询", notes = "出发站-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CytStartStation cytStartStation,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CytStartStation> queryWrapper = QueryGenerator.initQueryWrapper(cytStartStation, req.getParameterMap());
        Page<CytStartStation> page = new Page<CytStartStation>(pageNo, pageSize);
        IPage<CytStartStation> pageList = cytStartStationService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cytStartStation
     * @return
     */
    @AutoLog(value = "出发站-添加")
    @ApiOperation(value = "出发站-添加", notes = "出发站-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CytStartStation cytStartStation) {
        cytStartStationService.save(cytStartStation);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cytStartStation
     * @return
     */
    @AutoLog(value = "出发站-编辑")
    @ApiOperation(value = "出发站-编辑", notes = "出发站-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CytStartStation cytStartStation) {
        cytStartStationService.updateById(cytStartStation);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "出发站-通过id删除")
    @ApiOperation(value = "出发站-通过id删除", notes = "出发站-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cytStartStationService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "出发站-批量删除")
    @ApiOperation(value = "出发站-批量删除", notes = "出发站-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cytStartStationService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "出发站-通过id查询")
    @ApiOperation(value = "出发站-通过id查询", notes = "出发站-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CytStartStation cytStartStation = cytStartStationService.getById(id);
        if (cytStartStation == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cytStartStation);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cytStartStation
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CytStartStation cytStartStation) {
        return super.exportXls(request, cytStartStation, CytStartStation.class, "出发站");
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
        return super.importExcel(request, response, CytStartStation.class);
    }

    /**
     * 更新盛威出发站数据
     *
     * @return
     */
    @AutoLog(value = "出发站-更新盛威出发站数据")
    @ApiOperation(value = "出发站-更新盛威出发站数据", notes = "出发站-更新盛威出发站数据")
    @GetMapping(value = "/updateStartStation")
    public Result<?> updateStartStation() {
        return cytStartStationService.updateStartStation();
    }

    /**
     * 获取出发站数据
     *
     * @return
     */
    @AutoLog(value = "出发站-获取出发站数据")
    @ApiOperation(value = "出发站-获取出发站数据", notes = "出发站-获取出发站数据")
    @GetMapping(value = "/getStartStations")
    public Result<?> getStartStation() {
        Object ob = redisUtil.get("letour-tickets-getStartStations");
        if (ob != null) {
            return Result.OK(ob);
        } else {
            List<CytStartStation> cytStartStations = cytStartStationService.list(new QueryWrapper<CytStartStation>()
                    .eq("is_can_sell", 1)
                    .groupBy("city")
                    .select("departorg_name", "departorg_code", "departcity_id", "city"));
            if (cytStartStations.size()>0){
                redisUtil.set("letour-tickets-getStartStations", cytStartStations);
            }
            return Result.OK(cytStartStations);
        }
    }
}
