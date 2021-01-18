package org.jeecg.modules.seat.entity;

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
 * @Description: 座位信息
 * @Author: 刘杨刚
 * @Date:   2021-01-14
 * @Version: V1.0
 */
@Data
@TableName("cyt_seat_details")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_seat_details对象", description="座位信息")
public class CytSeatDetails implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**发车站编码*/
	@Excel(name = "发车站编码", width = 15)
    @ApiModelProperty(value = "发车站编码")
    private java.lang.String departorgCode;
	/**发车日期*/
	@Excel(name = "发车日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发车日期")
    private java.util.Date departDate;
	/**班次代码*/
	@Excel(name = "班次代码", width = 15)
    @ApiModelProperty(value = "班次代码")
    private java.lang.String scheduleCode;
	/**座位信息明细*/
	@Excel(name = "座位信息明细", width = 15)
    @ApiModelProperty(value = "座位信息明细")
    private java.lang.String opseatDetails;
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
