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
import org.jeecg.modules.station.entity.CytEndStation;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Api(tags = "到达站")
@RestController
@RequestMapping("/station/cytEndStation")
@Slf4j
public class CytEndStationController extends JeecgController<CytEndStation, ICytEndStationService> {
    @Autowired
    private ICytEndStationService cytEndStationService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param cytEndStation
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "到达站-分页列表查询")
    @ApiOperation(value = "到达站-分页列表查询", notes = "到达站-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CytEndStation cytEndStation,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CytEndStation> queryWrapper = QueryGenerator.initQueryWrapper(cytEndStation, req.getParameterMap());
        Page<CytEndStation> page = new Page<CytEndStation>(pageNo, pageSize);
        IPage<CytEndStation> pageList = cytEndStationService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cytEndStation
     * @return
     */
    @AutoLog(value = "到达站-添加")
    @ApiOperation(value = "到达站-添加", notes = "到达站-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CytEndStation cytEndStation) {
        cytEndStationService.save(cytEndStation);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cytEndStation
     * @return
     */
    @AutoLog(value = "到达站-编辑")
    @ApiOperation(value = "到达站-编辑", notes = "到达站-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CytEndStation cytEndStation) {
        cytEndStationService.updateById(cytEndStation);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "到达站-通过id删除")
    @ApiOperation(value = "到达站-通过id删除", notes = "到达站-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cytEndStationService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "到达站-批量删除")
    @ApiOperation(value = "到达站-批量删除", notes = "到达站-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cytEndStationService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "到达站-通过id查询")
    @ApiOperation(value = "到达站-通过id查询", notes = "到达站-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CytEndStation cytEndStation = cytEndStationService.getById(id);
        if (cytEndStation == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cytEndStation);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cytEndStation
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CytEndStation cytEndStation) {
        return super.exportXls(request, cytEndStation, CytEndStation.class, "到达站");
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
        return super.importExcel(request, response, CytEndStation.class);
    }

    /**
     * 更新到达站数据--盛威
     *
     * @return
     */
    @AutoLog(value = "到达站-更新盛威到达站数据")
    @ApiOperation(value = "到达站-更新盛威到达站数据", notes = "到达站-更新盛威到达站数据")
    @GetMapping(value = "/updateEndStation")
    public Result<?> updateEndStation() {
        return cytEndStationService.updateEndStation();
    }

    /**
     * 获取到达站数据
     *
     * @return
     */
    @AutoLog(value = "到达站-获取到达站数据")
    @ApiOperation(value = "到达站-获取到达站数据", notes = "到达站-获取到达站数据")
    @GetMapping(value = "/getEndStations")
    public Result<?> getEndStations(@RequestParam String departcityId) {
        Object ob = redisUtil.hget("letour-tickets-getEndStations", departcityId);
        if (ob != null) {
            return Result.OK(ob);
        } else {
            List<CytEndStation> cytStartStations = cytEndStationService.list(new QueryWrapper<CytEndStation>()
                    .eq("is_can_sell", 1)
                    .eq("departcity_id", departcityId)
                    .groupBy("name")
                    .orderByAsc("name_jianpin")
                    .select("orgname", "name", "departcity_id", "CHAR(INTERVAL(CONV(HEX(left(convert( name using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));

            List<CytEndStation> pyList = cytEndStationService.list(new QueryWrapper<CytEndStation>()
                    .groupBy("py")
                    .select("CHAR(INTERVAL(CONV(HEX(left(convert( name using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));
            Map<String, Object> map = new LinkedHashMap<>();
            for (CytEndStation py : pyList) {
                List<CytEndStation> list = new ArrayList<>();
                for (CytEndStation cytEndStation : cytStartStations) {
                    if (py.getPy().equals(cytEndStation.getPy())) {
                        list.add(cytEndStation);
                    }
                }
                map.put(py.getPy(), list);
            }
            if (!map.isEmpty()) {
                redisUtil.hset("letour-tickets-getEndStations", departcityId, map);
            }
            return Result.OK(map);
        }
    }
}
