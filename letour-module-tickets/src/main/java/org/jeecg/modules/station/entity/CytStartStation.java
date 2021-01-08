package org.jeecg.modules.station.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 出发站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Data
@TableName("cyt_start_station")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "cyt_start_station对象", description = "出发站")
public class CytStartStation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
    /**
     * 客运站名称
     */
    @Excel(name = "客运站名称", width = 15)
    @ApiModelProperty(value = "客运站名称")
    private java.lang.String departorgName;
    /**
     * 客运站编码
     */
    @Excel(name = "客运站编码", width = 15)
    @ApiModelProperty(value = "客运站编码")
    private java.lang.String departorgCode;
    /**
     * 城市id
     */
    @Excel(name = "城市id", width = 15)
    @ApiModelProperty(value = "城市id")
    private java.lang.String departcityId;
    /**
     * 城市名称
     */
    @Excel(name = "城市名称", width = 15)
    @ApiModelProperty(value = "城市名称")
    private java.lang.String city;
    /**
     * 是否可售
     */
    @Excel(name = "是否可售", width = 15, dicCode = "yn")
    @Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否可售")
    private java.lang.Integer isCanSell;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
}
