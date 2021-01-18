package org.jeecg.modules.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 订单信息
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@ApiModel(value="cyt_order对象", description="订单信息")
@Data
@TableName("cyt_order")
public class CytOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private java.lang.String orderId;
	/**发车站名称*/
	@Excel(name = "发车站名称", width = 15)
    @ApiModelProperty(value = "发车站名称")
    private java.lang.String departorgName;
	/**发车站编码*/
	@Excel(name = "发车站编码", width = 15)
    @ApiModelProperty(value = "发车站编码")
    private java.lang.String departorgCode;
	/**到达站名称*/
	@Excel(name = "到达站名称", width = 15)
    @ApiModelProperty(value = "到达站名称")
    private java.lang.String reachstationName;
	/**到达站编码*/
	@Excel(name = "到达站编码", width = 15)
    @ApiModelProperty(value = "到达站编码")
    private java.lang.String reachstationCode;
	/**发车日期*/
	@Excel(name = "发车日期", width = 15)
    @ApiModelProperty(value = "发车日期")
    private java.lang.String departDate;
	/**发车时间*/
	@Excel(name = "发车时间", width = 15)
    @ApiModelProperty(value = "发车时间")
    private java.lang.String departTime;
	/**班次编码*/
	@Excel(name = "班次编码", width = 15)
    @ApiModelProperty(value = "班次编码")
    private java.lang.String scheduleCode;
	/**总票数*/
	@Excel(name = "总票数", width = 15)
    @ApiModelProperty(value = "总票数")
    private java.lang.Integer ticketCount;
	/**保票数*/
	@Excel(name = "保票数", width = 15)
    @ApiModelProperty(value = "保票数")
    private java.lang.Integer insurceCount;
	/**锁座时间*/
	@Excel(name = "锁座时间", width = 15)
    @ApiModelProperty(value = "锁座时间")
    private java.lang.String lockTime;
	/**解锁时间*/
	@Excel(name = "解锁时间", width = 15)
    @ApiModelProperty(value = "解锁时间")
    private java.lang.String unlockTime;
	/**座位类型*/
	@Excel(name = "座位类型", width = 15)
    @ApiModelProperty(value = "座位类型")
    private java.lang.String seatType;
	/**出发城市*/
	@Excel(name = "出发城市", width = 15)
    @ApiModelProperty(value = "出发城市")
    private java.lang.String departCity;
	/**车辆类型*/
	@Excel(name = "车辆类型", width = 15)
    @ApiModelProperty(value = "车辆类型")
    private java.lang.String vehicleType;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private java.lang.String vehicleno;
	/**里程*/
	@Excel(name = "里程", width = 15)
    @ApiModelProperty(value = "里程")
    private java.lang.String distance;
	/**检票口*/
	@Excel(name = "检票口", width = 15)
    @ApiModelProperty(value = "检票口")
    private java.lang.String tickeTentrance;
	/**候车厅*/
	@Excel(name = "候车厅", width = 15)
    @ApiModelProperty(value = "候车厅")
    private java.lang.String waitingRoom;
	/**乘车卡位*/
	@Excel(name = "乘车卡位", width = 15)
    @ApiModelProperty(value = "乘车卡位")
    private java.lang.String buspark;
	/**班次类型:0:普通、1:直达、2商务，3旅游专线*/
	@Excel(name = "班次类型:0:普通、1:直达、2商务，3旅游专线", width = 15)
    @ApiModelProperty(value = "班次类型:0:普通、1:直达、2商务，3旅游专线")
    private java.lang.String scheduleType;
	/**是否加班:0:正班、1:加班*/
	@Excel(name = "是否加班:0:正班、1:加班", width = 15)
    @ApiModelProperty(value = "是否加班:0:正班、1:加班")
    private java.lang.String isoverTime;
	/**是否流水班:1为流水班， 0为非流水班*/
	@Excel(name = "是否流水班:1为流水班， 0为非流水班", width = 15)
    @ApiModelProperty(value = "是否流水班:1为流水班， 0为非流水班")
    private java.lang.String isLineSchedule;
	/**始发时间*/
	@Excel(name = "始发时间", width = 15)
    @ApiModelProperty(value = "始发时间")
    private java.lang.String startTime;
	/**末班时间*/
	@Excel(name = "末班时间", width = 15)
    @ApiModelProperty(value = "末班时间")
    private java.lang.String endTime;
	/**订单总金额*/
	@Excel(name = "订单总金额", width = 15)
    @ApiModelProperty(value = "订单总金额")
    private java.math.BigDecimal totalPrice;
	/**订单状态0：新建立 1：己完成 2：售票失败 3：己超时 4：己注销*/
	@Excel(name = "订单状态0：新建立 1：己完成 2：售票失败 3：己超时 4：己注销", width = 15)
    @ApiModelProperty(value = "订单状态0：新建立 1：己完成 2：售票失败 3：己超时 4：己注销")
    private java.lang.String status;
	/**是否改签0：未改签1：已改签*/
	@Excel(name = "是否改签0：未改签1：已改签", width = 15)
    @ApiModelProperty(value = "是否改签0：未改签1：已改签")
    private java.lang.Integer isChangeTicket;
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
