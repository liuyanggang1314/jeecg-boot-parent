package org.jeecg.modules.rebook.controller;

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
import org.jeecg.modules.rebook.entity.CytRebook;
import org.jeecg.modules.rebook.service.ICytRebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 改签记录
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Api(tags="改签记录")
@RestController
@RequestMapping("/rebook/cytRebook")
@Slf4j
public class CytRebookController extends JeecgController<CytRebook, ICytRebookService> {
	@Autowired
	private ICytRebookService cytRebookService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cytRebook
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "改签记录-分页列表查询")
	@ApiOperation(value="改签记录-分页列表查询", notes="改签记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CytRebook cytRebook,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CytRebook> queryWrapper = QueryGenerator.initQueryWrapper(cytRebook, req.getParameterMap());
		Page<CytRebook> page = new Page<CytRebook>(pageNo, pageSize);
		IPage<CytRebook> pageList = cytRebookService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param cytRebook
	 * @return
	 */
	@AutoLog(value = "改签记录-添加")
	@ApiOperation(value="改签记录-添加", notes="改签记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CytRebook cytRebook) {
		cytRebookService.save(cytRebook);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param cytRebook
	 * @return
	 */
	@AutoLog(value = "改签记录-编辑")
	@ApiOperation(value="改签记录-编辑", notes="改签记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CytRebook cytRebook) {
		cytRebookService.updateById(cytRebook);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "改签记录-通过id删除")
	@ApiOperation(value="改签记录-通过id删除", notes="改签记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cytRebookService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "改签记录-批量删除")
	@ApiOperation(value="改签记录-批量删除", notes="改签记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cytRebookService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "改签记录-通过id查询")
	@ApiOperation(value="改签记录-通过id查询", notes="改签记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CytRebook cytRebook = cytRebookService.getById(id);
		if(cytRebook==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(cytRebook);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param cytRebook
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CytRebook cytRebook) {
        return super.exportXls(request, cytRebook, CytRebook.class, "改签记录");
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
        return super.importExcel(request, response, CytRebook.class);
    }

}
