package org.jeecg.modules.insurance.entity;

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
 * @Description: 保险订单
 * @Author: 刘杨刚
 * @Date:   2021-01-14
 * @Version: V1.0
 */
@Data
@TableName("cyt_insurance_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_insurance_info对象", description="保险订单")
public class CytInsuranceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
    @ApiModelProperty(value = "用户ID")
    private java.lang.String userId;
	/**系统保险单号*/
	@Excel(name = "系统保险单号", width = 15)
    @ApiModelProperty(value = "系统保险单号")
    private java.lang.String orderId;
	/**票号*/
	@Excel(name = "票号", width = 15)
    @ApiModelProperty(value = "票号")
    private java.lang.String insuranceId;
	/**保险单价*/
	@Excel(name = "保险单价", width = 15)
    @ApiModelProperty(value = "保险单价")
    private java.lang.String insurancePrice;
	/**保险类型*/
	@Excel(name = "保险类型", width = 15)
    @ApiModelProperty(value = "保险类型")
    private java.lang.String productCode;
	/**承保人*/
	@Excel(name = "承保人", width = 15)
    @ApiModelProperty(value = "承保人")
    private java.lang.String name;
	/**承保人身份证*/
	@Excel(name = "承保人身份证", width = 15)
    @ApiModelProperty(value = "承保人身份证")
    private java.lang.String idCard;
	/**承保人电话*/
	@Excel(name = "承保人电话", width = 15)
    @ApiModelProperty(value = "承保人电话")
    private java.lang.String phone;
	/**承保人性别*/
	@Excel(name = "承保人性别", width = 15)
    @ApiModelProperty(value = "承保人性别")
    private java.lang.String sex;
	/**承保人生日*/
	@Excel(name = "承保人生日", width = 15)
    @ApiModelProperty(value = "承保人生日")
    private java.lang.String birthday;
	/**发车日期*/
	@Excel(name = "发车日期", width = 15)
    @ApiModelProperty(value = "发车日期")
    private java.lang.String departDate;
	/**发车时间*/
	@Excel(name = "发车时间", width = 15)
    @ApiModelProperty(value = "发车时间")
    private java.lang.String departTime;
	/**保险公司单号*/
	@Excel(name = "保险公司单号", width = 15)
    @ApiModelProperty(value = "保险公司单号")
    private java.lang.String insuranceCompanyNumber;
	/**信息*/
	@Excel(name = "信息", width = 15)
    @ApiModelProperty(value = "信息")
    private java.lang.String info;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15)
    @ApiModelProperty(value = "订单状态")
    private java.lang.Integer status;
	/**座位号*/
	@Excel(name = "座位号", width = 15)
    @ApiModelProperty(value = "座位号")
    private java.lang.Integer seatNo;
	/**保险查询地址*/
	@Excel(name = "保险查询地址", width = 15)
    @ApiModelProperty(value = "保险查询地址")
    private java.lang.String insuranceUrl;
	/**退保时间*/
	@Excel(name = "退保时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "退保时间")
    private java.util.Date refundTime;
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
