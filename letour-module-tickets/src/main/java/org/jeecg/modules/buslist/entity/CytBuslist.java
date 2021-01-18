package org.jeecg.modules.buslist.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 班次列表
 * @Author: 刘杨刚
 * @Date:   2021-01-11
 * @Version: V1.0
 */
@Data
@TableName("cyt_buslist")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_buslist对象", description="班次列表")
public class CytBuslist implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**班次 hashid*/
	@Excel(name = "班次 hashid", width = 15)
    @ApiModelProperty(value = "班次 hashid")
    private java.lang.Integer hashId;
	/**发车城市名称*/
	@Excel(name = "发车城市名称", width = 15)
    @ApiModelProperty(value = "发车城市名称")
    private java.lang.String departCityName;
	/**发车站所属省份*/
	@Excel(name = "发车站所属省份", width = 15)
    @ApiModelProperty(value = "发车站所属省份")
    private java.lang.String departProvince;
	/**发车站编码-出发车站编码*/
	@Excel(name = "发车站编码-出发车站编码", width = 15)
    @ApiModelProperty(value = "发车站编码-出发车站编码")
    private java.lang.String departorgCode;
	/**班次编码*/
	@Excel(name = "班次编码", width = 15)
    @ApiModelProperty(value = "班次编码")
    private java.lang.String scheduleCode;
	/**发车站名称*/
	@Excel(name = "发车站名称", width = 15)
    @ApiModelProperty(value = "发车站名称")
    private java.lang.String departorgName;
	/**发车时间*/
	@Excel(name = "发车时间", width = 15)
    @ApiModelProperty(value = "发车时间")
    private java.lang.String departTime;
	/**截至时间-流水班使用*/
	@Excel(name = "截至时间-流水班使用", width = 15)
    @ApiModelProperty(value = "截至时间-流水班使用")
    private java.lang.String endTime;
	/**座位类型*/
	@Excel(name = "座位类型", width = 15)
    @ApiModelProperty(value = "座位类型")
    private java.lang.String seatType;
	/**总座位数*/
	@Excel(name = "总座位数", width = 15)
    @ApiModelProperty(value = "总座位数")
    private java.lang.Integer seatNumber;
	/**余票数*/
	@Excel(name = "余票数", width = 15)
    @ApiModelProperty(value = "余票数")
    private java.lang.Integer residualNumber;
	/**已售座位数*/
	@Excel(name = "已售座位数", width = 15)
    @ApiModelProperty(value = "已售座位数")
    private java.lang.Integer selledNumber;
	/**剩余携童票座数*/
	@Excel(name = "剩余携童票座数", width = 15)
    @ApiModelProperty(value = "剩余携童票座数")
    private java.lang.Integer remainKidSeats;
	/**是否是加班车-1：加班0：正常 */
	@Excel(name = "是否是加班车-1：加班0：正常 ", width = 15)
    @ApiModelProperty(value = "是否是加班车-1：加班0：正常 ")
    private java.lang.Integer isAddSchedule;
	/**是否是流水班-1：流水0：正常*/
	@Excel(name = "是否是流水班-1：流水0：正常", width = 15)
    @ApiModelProperty(value = "是否是流水班-1：流水0：正常")
    private java.lang.Integer isLineSchedule;
	/**全票价*/
	@Excel(name = "全票价", width = 15)
    @ApiModelProperty(value = "全票价")
    private java.lang.Double fullPrice;
	/**优惠票价*/
	@Excel(name = "优惠票价", width = 15)
    @ApiModelProperty(value = "优惠票价")
    private java.lang.Double discountPrice;
	/**学生票价*/
	@Excel(name = "学生票价", width = 15)
    @ApiModelProperty(value = "学生票价")
    private java.lang.Double studentPrice;
	/**半票价*/
	@Excel(name = "半票价", width = 15)
    @ApiModelProperty(value = "半票价")
    private java.lang.Double halfPrice;
	/**折扣票价*/
	@Excel(name = "折扣票价", width = 15)
    @ApiModelProperty(value = "折扣票价")
    private java.lang.Double rebatePrice;
	/**往返票价*/
	@Excel(name = "往返票价", width = 15)
    @ApiModelProperty(value = "往返票价")
    private java.lang.Double returnPrice;
	/**联程票价*/
	@Excel(name = "联程票价", width = 15)
    @ApiModelProperty(value = "联程票价")
    private java.lang.Double tripPrice;
	/**到达站编码*/
	@Excel(name = "到达站编码", width = 15)
    @ApiModelProperty(value = "到达站编码")
    private java.lang.String reachStationCode;
	/**到达站名称*/
	@Excel(name = "到达站名称", width = 15)
    @ApiModelProperty(value = "到达站名称")
    private java.lang.String reachStationName;
	/**到达城市名称*/
	@Excel(name = "到达城市名称", width = 15)
    @ApiModelProperty(value = "到达城市名称")
    private java.lang.String reachCityName;
	/**到达站所属省份*/
	@Excel(name = "到达站所属省份", width = 15)
    @ApiModelProperty(value = "到达站所属省份")
    private java.lang.String reachProvince;
	/**终到站编码*/
	@Excel(name = "终到站编码", width = 15)
    @ApiModelProperty(value = "终到站编码")
    private java.lang.String endStationCode;
	/**终到站名称*/
	@Excel(name = "终到站名称", width = 15)
    @ApiModelProperty(value = "终到站名称")
    private java.lang.String endStationName;
	/**运行时间*/
	@Excel(name = "运行时间", width = 15)
    @ApiModelProperty(value = "运行时间")
    private java.lang.Integer runTime;
	/**运行里程*/
	@Excel(name = "运行里程", width = 15)
    @ApiModelProperty(value = "运行里程")
    private java.lang.Integer runDistance;
	/**车辆类型-1：特大型、2：大型、3：中型、4：小型*/
	@Excel(name = "车辆类型-1：特大型、2：大型、3：中型、4：小型", width = 15)
    @ApiModelProperty(value = "车辆类型-1：特大型、2：大型、3：中型、4：小型")
    private java.lang.String vehicleType;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private java.lang.String vehicleNo;
	/**线路编码*/
	@Excel(name = "线路编码", width = 15)
    @ApiModelProperty(value = "线路编码")
    private java.lang.String routCode;
	/**线路名称*/
	@Excel(name = "线路名称", width = 15)
    @ApiModelProperty(value = "线路名称")
    private java.lang.String routName;
	/**车辆等级-1 高三级，2高二级，3高一级 ，4中级，5 普通级*/
	@Excel(name = "车辆等级-1 高三级，2高二级，3高一级 ，4中级，5 普通级", width = 15)
    @ApiModelProperty(value = "车辆等级-1 高三级，2高二级，3高一级 ，4中级，5 普通级")
    private java.lang.String busRank;
	/**车辆品牌*/
	@Excel(name = "车辆品牌", width = 15)
    @ApiModelProperty(value = "车辆品牌")
    private java.lang.String busBrand;
	/**是否可售-0不可售，1可售*/
	@Excel(name = "是否可售-0不可售，1可售", width = 15)
    @ApiModelProperty(value = "是否可售-0不可售，1可售")
    private java.lang.Integer isCanSell;
	/**检票口*/
	@Excel(name = "检票口", width = 15)
    @ApiModelProperty(value = "检票口")
    private java.lang.String tickeTentrance;
	/**候车厅号*/
	@Excel(name = "候车厅号", width = 15)
    @ApiModelProperty(value = "候车厅号")
    private java.lang.String waitingRoom;
	/**班次起始时间*/
	@Excel(name = "班次起始时间", width = 15)
    @ApiModelProperty(value = "班次起始时间")
    private java.lang.String startTime;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
    private java.lang.String unitName;
	/**城市id*/
	@Excel(name = "城市id", width = 15)
    @ApiModelProperty(value = "城市id")
    private java.lang.String departcityId;
	/**班次时间*/
	@Excel(name = "班次时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "班次时间")
    private java.util.Date buslistTime;
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
