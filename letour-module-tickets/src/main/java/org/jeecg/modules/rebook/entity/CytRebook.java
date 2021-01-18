package org.jeecg.modules.rebook.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 改签记录
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Data
@TableName("cyt_rebook")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_rebook对象", description="改签记录")
public class CytRebook implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编码*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编码")
    private java.lang.String id;
	/**改签前订单号*/
	@Excel(name = "改签前订单号", width = 15)
    @ApiModelProperty(value = "改签前订单号")
    private java.lang.String oldOrdeId;
	/**改签后订单号*/
	@Excel(name = "改签后订单号", width = 15)
    @ApiModelProperty(value = "改签后订单号")
    private java.lang.String newOrderId;
	/**改签时间*/
	@Excel(name = "改签时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "改签时间")
    private java.util.Date changeTime;
	/**改签费用*/
	@Excel(name = "改签费用", width = 15)
    @ApiModelProperty(value = "改签费用")
    private java.math.BigDecimal changeFee;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
}
