package org.jeecg.modules.rider.entity;

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
 * @Description: 乘车人列表
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Data
@TableName("cyt_rider")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="cyt_rider对象", description="乘车人列表")
public class CytRider implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**微信id*/
	@Excel(name = "微信id", width = 15)
    @ApiModelProperty(value = "微信id")
    private java.lang.String userId;
	/**联系人姓名*/
	@Excel(name = "联系人姓名", width = 15)
    @ApiModelProperty(value = "联系人姓名")
    private java.lang.String username;
	/**联系人联系方式*/
	@Excel(name = "联系人联系方式", width = 15)
    @ApiModelProperty(value = "联系人联系方式")
    private java.lang.String phone;
	/**证件类型0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证*/
	@Excel(name = "证件类型0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证", width = 15)
    @ApiModelProperty(value = "证件类型0：身份证，1：学生证，2：军官证，3：驾驶证，4：护照，5：港澳通行证")
    private java.lang.String certificateType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private java.lang.String certificateno;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
	/**状态 0待审核，1通过*/
	@Excel(name = "状态 0待审核，1通过", width = 15)
    @ApiModelProperty(value = "状态 0待审核，1通过")
    private java.lang.String status;
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
