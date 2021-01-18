package org.jeecg.modules.order.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 车票信息
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@ApiModel(value="cyt_order对象", description="订单信息")
@Data
@TableName("cyt_tickets")
public class CytTickets implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**订单号*/
	@ApiModelProperty(value = "订单号")
	private java.lang.String orderId;
	/**票种*/
	@Excel(name = "票种", width = 15)
	@ApiModelProperty(value = "票种")
	private java.lang.String ticketType;
	/**电子票ID*/
	@Excel(name = "电子票ID", width = 15)
	@ApiModelProperty(value = "电子票ID")
	private java.lang.String netticketId;
	/**票价*/
	@Excel(name = "票价", width = 15)
	@ApiModelProperty(value = "票价")
	private java.lang.Double price;
	/**座位号*/
	@Excel(name = "座位号", width = 15)
	@ApiModelProperty(value = "座位号")
	private java.lang.String seatno;
	/**是否带儿童*/
	@Excel(name = "是否带儿童", width = 15)
	@ApiModelProperty(value = "是否带儿童")
	private java.lang.String isRemainKid;
	/**坐型*/
	@Excel(name = "坐型", width = 15)
	@ApiModelProperty(value = "坐型")
	private java.lang.String seatType;
	/**打印票号如果代售票当时就打印车票，则此字段不为空，网站售票时该字段传电子票号给车站*/
	@Excel(name = "打印票号如果代售票当时就打印车票，则此字段不为空，网站售票时该字段传电子票号给车站", width = 15)
	@ApiModelProperty(value = "打印票号如果代售票当时就打印车票，则此字段不为空，网站售票时该字段传电子票号给车站")
	private java.lang.String ticketno;
	/**服务费*/
	@Excel(name = "服务费", width = 15)
	@ApiModelProperty(value = "服务费")
	private java.lang.Double serviceFee;
	/**取票密码*/
	@Excel(name = "取票密码", width = 15)
	@ApiModelProperty(value = "取票密码")
	private java.lang.String verificationCode;
	/**乘客姓名*/
	@Excel(name = "乘客姓名", width = 15)
	@ApiModelProperty(value = "乘客姓名")
	private java.lang.String customerName;
	/**证件类型证件类型：0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证*/
	@Excel(name = "证件类型证件类型：0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证", width = 15)
	@ApiModelProperty(value = "证件类型证件类型：0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证")
	private java.lang.Integer certificateType;
	/**证件号*/
	@Excel(name = "证件号", width = 15)
	@ApiModelProperty(value = "证件号")
	private java.lang.String certificateno;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
	@ApiModelProperty(value = "手机号")
	private java.lang.String phone;
	/**站务费*/
	@Excel(name = "站务费", width = 15)
	@ApiModelProperty(value = "站务费")
	private java.lang.Double stationServicefee;
	/**燃油附加费*/
	@Excel(name = "燃油附加费", width = 15)
	@ApiModelProperty(value = "燃油附加费")
	private java.lang.Double fuelPrice;
	/**售票时间*/
	@Excel(name = "售票时间", width = 15)
	@ApiModelProperty(value = "售票时间")
	private java.lang.String sellTime;
	/**保费*/
	@Excel(name = "保费", width = 15)
	@ApiModelProperty(value = "保费")
	private java.lang.Double premium;
	/**保险票号*/
	@Excel(name = "保险票号", width = 15)
	@ApiModelProperty(value = "保险票号")
	private java.lang.String insureprintno;
	/**保险产品种类编码*/
	@Excel(name = "保险产品种类编码", width = 15)
	@ApiModelProperty(value = "保险产品种类编码")
	private java.lang.String insureType;
	/**意外伤害保险金额*/
	@Excel(name = "意外伤害保险金额", width = 15)
	@ApiModelProperty(value = "意外伤害保险金额")
	private java.lang.Double insureeMoney;
	/**意外伤害医疗保险金额*/
	@Excel(name = "意外伤害医疗保险金额", width = 15)
	@ApiModelProperty(value = "意外伤害医疗保险金额")
	private java.lang.Double insureemMoney;
	/**保险单号*/
	@Excel(name = "保险单号", width = 15)
	@ApiModelProperty(value = "保险单号")
	private java.lang.String insuranceno;
	/**保险公司名称*/
	@Excel(name = "保险公司名称", width = 15)
	@ApiModelProperty(value = "保险公司名称")
	private java.lang.String insureCompanyName;
	/**预留字段*/
	@Excel(name = "预留字段", width = 15)
	@ApiModelProperty(value = "预留字段")
	private java.lang.String temp1;
	/**预留字段*/
	@Excel(name = "预留字段", width = 15)
	@ApiModelProperty(value = "预留字段")
	private java.lang.String temp2;
	/**售票附加信息*/
	@Excel(name = "售票附加信息", width = 15)
	@ApiModelProperty(value = "售票附加信息")
	private java.lang.String extendsInfo;
	/**旅客性别*/
	@Excel(name = "旅客性别", width = 15)
	@ApiModelProperty(value = "旅客性别")
	private java.lang.String sex;
	/**车票状态0：正常1：退票2：废票3：已检票*/
	@Excel(name = "车票状态0：正常1：退票2：废票3：已检票", width = 15)
	@ApiModelProperty(value = "车票状态0：正常1：退票2：废票3：已检票")
	private java.lang.Integer ticketStatus;
	/**取票状态0：无需取票1：未取票2：已取票*/
	@Excel(name = "取票状态0：无需取票1：未取票2：已取票", width = 15)
	@ApiModelProperty(value = "取票状态0：无需取票1：未取票2：已取票")
	private java.lang.Integer takeStatus;
	/**退票手续费*/
	@Excel(name = "退票手续费", width = 15)
	@ApiModelProperty(value = "退票手续费")
	private java.lang.Double returnfee;
	/**退票费率*/
	@Excel(name = "退票费率", width = 15)
	@ApiModelProperty(value = "退票费率")
	private java.lang.String returnRate;
	/**退票时间yyyyMMddHHmmss*/
	@Excel(name = "退票时间yyyyMMddHHmmss", width = 15)
	@ApiModelProperty(value = "退票时间yyyyMMddHHmmss")
	private java.lang.String returnTime;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
	@ApiModelProperty(value = "车牌号")
	private java.lang.String vehicleno;
	/**退票凭证号*/
	@Excel(name = "退票凭证号", width = 15)
	@ApiModelProperty(value = "退票凭证号")
	private java.lang.String returnvoucherno;
	/**退票方式0：网站退票、1：取票客户端退票、2：车站站务系统退票*/
	@Excel(name = "退票方式0：网站退票、1：取票客户端退票、2：车站站务系统退票", width = 15)
	@ApiModelProperty(value = "退票方式0：网站退票、1：取票客户端退票、2：车站站务系统退票")
	private java.lang.String returnWay;
	/**返款模式退票款返回模式（0：怎么付款怎么返回   1：车站垫付现金）*/
	@Excel(name = "返款模式退票款返回模式（0：怎么付款怎么返回   1：车站垫付现金）", width = 15)
	@ApiModelProperty(value = "返款模式退票款返回模式（0：怎么付款怎么返回   1：车站垫付现金）")
	private java.lang.String returnFeemode;
	/**退票机构*/
	@Excel(name = "退票机构", width = 15)
	@ApiModelProperty(value = "退票机构")
	private java.lang.String returnorgCode;
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
