package org.jeecg.modules.station.entity;

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
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date:   2021-01-08
 * @Version: V1.0
 */
@Data
@TableName("cyt_end_station")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_end_station对象", description="到达站")
public class CytEndStation implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**客运站编码*/
	@Excel(name = "客运站编码", width = 15)
    @ApiModelProperty(value = "客运站编码")
    private java.lang.String orgcode;
	/**客运站编码名称*/
	@Excel(name = "客运站编码名称", width = 15)
    @ApiModelProperty(value = "客运站编码名称")
    private java.lang.String orgname;
	/**站点名称*/
	@Excel(name = "站点名称", width = 15)
    @ApiModelProperty(value = "站点名称")
    private java.lang.String name;
	/**省份*/
	@Excel(name = "省份", width = 15)
    @ApiModelProperty(value = "省份")
    private java.lang.String province;
	/**拼音简称*/
	@Excel(name = "拼音简称", width = 15)
    @ApiModelProperty(value = "拼音简称")
    private java.lang.String nameJianpin;
	/**站点编码*/
	@Excel(name = "站点编码", width = 15)
    @ApiModelProperty(value = "站点编码")
    private java.lang.String reachstationcode;
	/**地区*/
	@Excel(name = "地区", width = 15)
    @ApiModelProperty(value = "地区")
    private java.lang.String city;
	/**是否可售*/
	@Excel(name = "是否可售", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否可售")
    private java.lang.Integer isCanSell;
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
