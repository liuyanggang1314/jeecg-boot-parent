package org.jeecg.modules.seat.controller;

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
import org.jeecg.modules.seat.entity.CytSeatDetails;
import org.jeecg.modules.seat.service.ICytSeatDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 座位信息
 * @Author: 刘杨刚
 * @Date: 2021-01-14
 * @Version: V1.0
 */
@Api(tags = "座位信息")
@RestController
@RequestMapping("/seat/cytSeatDetails")
@Slf4j
public class CytSeatDetailsController extends JeecgController<CytSeatDetails, ICytSeatDetailsService> {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICytSeatDetailsService cytSeatDetailsService;

    /**
     * 分页列表查询
     *
     * @param cytSeatDetails
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "座位信息-分页列表查询")
    @ApiOperation(value = "座位信息-分页列表查询", notes = "座位信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CytSeatDetails cytSeatDetails,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CytSeatDetails> queryWrapper = QueryGenerator.initQueryWrapper(cytSeatDetails, req.getParameterMap());
        Page<CytSeatDetails> page = new Page<CytSeatDetails>(pageNo, pageSize);
        IPage<CytSeatDetails> pageList = cytSeatDetailsService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param cytSeatDetails
     * @return
     */
    @AutoLog(value = "座位信息-添加")
    @ApiOperation(value = "座位信息-添加", notes = "座位信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CytSeatDetails cytSeatDetails) {
        cytSeatDetailsService.save(cytSeatDetails);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param cytSeatDetails
     * @return
     */
    @AutoLog(value = "座位信息-编辑")
    @ApiOperation(value = "座位信息-编辑", notes = "座位信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CytSeatDetails cytSeatDetails) {
        cytSeatDetailsService.updateById(cytSeatDetails);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "座位信息-通过id删除")
    @ApiOperation(value = "座位信息-通过id删除", notes = "座位信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cytSeatDetailsService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "座位信息-批量删除")
    @ApiOperation(value = "座位信息-批量删除", notes = "座位信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cytSeatDetailsService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "座位信息-通过id查询")
    @ApiOperation(value = "座位信息-通过id查询", notes = "座位信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CytSeatDetails cytSeatDetails = cytSeatDetailsService.getById(id);
        if (cytSeatDetails == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(cytSeatDetails);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cytSeatDetails
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CytSeatDetails cytSeatDetails) {
        return super.exportXls(request, cytSeatDetails, CytSeatDetails.class, "座位信息");
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
        return super.importExcel(request, response, CytSeatDetails.class);
    }


    /**
     * 更新座位数据
     *
     * @return
     */
    @AutoLog(value = "座位信息-更新座位数据")
    @ApiOperation(value = "座位信息-更新座位数据", notes = "座位信息-更新座位数据")
    @GetMapping(value = "/updateSeatDetails")
    public Result<?> updateSeatDetails(@RequestParam String departorgCode, @RequestParam String departDate, @RequestParam String scheduleCode) {
        return cytSeatDetailsService.updateSeatDetail(departorgCode, departDate, scheduleCode);
    }


    /**
     * 获取座位数据
     *
     * @return
     */
    @AutoLog(value = "座位信息-获取座位数据")
    @ApiOperation(value = "座位信息-获取座位数据", notes = "座位信息-获取座位数据")
    @GetMapping(value = "/getSeatDetails")
    public Result<?> getSeatDetails(@RequestParam String departorgCode, @RequestParam String departDate, @RequestParam String scheduleCode) {
        return cytSeatDetailsService.getSeatDetails(departorgCode,departDate,scheduleCode);
    }

}
