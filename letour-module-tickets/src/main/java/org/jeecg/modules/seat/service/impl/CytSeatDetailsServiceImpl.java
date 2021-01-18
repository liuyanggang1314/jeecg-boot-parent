package org.jeecg.modules.seat.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.boot.starter.rabbitmq.client.RabbitMqClient;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.MD5Util;
import org.jeecg.common.util.RsaUtil;
import org.jeecg.modules.seat.entity.CytSeatDetails;
import org.jeecg.modules.seat.mapper.CytSeatDetailsMapper;
import org.jeecg.modules.seat.service.ICytSeatDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Description: 座位信息
 * @Author: 刘杨刚
 * @Date: 2021-01-14
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytSeatDetailsServiceImpl extends ServiceImpl<CytSeatDetailsMapper, CytSeatDetails> implements ICytSeatDetailsService {
    @Value("${letour.uid}")
    private String uid;
    @Autowired
    private RabbitMqClient rabbitMqClient;

    @Override
    public Result<String> updateSeatDetail(String departorgCode, String departDate, String scheduleCode) {
        Result<String> result = new Result<String>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", "1.0");
        jsonObject.put("uid", uid);
        jsonObject.put("departorgcode", departorgCode);
        jsonObject.put("departdate", departDate);
        jsonObject.put("schedulecode", scheduleCode);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("data", RsaUtil.encrypt(jsonObject.toJSONString()));
        paramMap.put("md5", MD5Util.MD5Encode(jsonObject.toJSONString(), null));

        String departorgs = HttpRequest
                .post("http://39.130.135.98:9011/openapi/2.0/queryscheduleseatsdetail")
                .form(paramMap)
                .execute()
                .body();
        JSONObject jsonObject1 = JSON.parseObject(departorgs);
        String data = RsaUtil.desEncrypt(jsonObject1.getString("data"));
        System.out.println(data);
        JSONObject opseatdetails = JSON.parseObject(data);
        if ("0000".equals(jsonObject.getString("rescode"))) {
            CytSeatDetails cytSeatDetails = new CytSeatDetails();
            cytSeatDetails.setDepartorgCode(departorgCode);
            cytSeatDetails.setDepartDate(DateUtil.parse(departDate));
            cytSeatDetails.setScheduleCode(scheduleCode);
            cytSeatDetails.setOpseatDetails(opseatdetails.getString("opseatdetails"));
            baseMapper.insert(cytSeatDetails);
            return result.success("更新成功");
        } else {
            return result.error500(jsonObject1.getString("resmsg"));
        }
    }
}
