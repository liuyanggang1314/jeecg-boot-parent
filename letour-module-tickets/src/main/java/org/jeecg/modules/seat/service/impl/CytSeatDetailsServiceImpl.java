package org.jeecg.modules.seat.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.MD5Util;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.RsaUtil;
import org.jeecg.modules.seat.entity.CytSeatDetails;
import org.jeecg.modules.seat.mapper.CytSeatDetailsMapper;
import org.jeecg.modules.seat.service.ICytSeatDetailsService;
import org.jeecg.modules.utils.TicketUtil;
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
@Slf4j
@Service
@DS("ticket")
public class CytSeatDetailsServiceImpl extends ServiceImpl<CytSeatDetailsMapper, CytSeatDetails> implements ICytSeatDetailsService {
    @Value("${letour.uid}")
    private String uid;
    @Value("${letour.url}")
    private String url;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result<?> updateSeatDetail(String departorgCode, String departDate, String scheduleCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", "1.0");
        jsonObject.put("uid", uid);
        jsonObject.put("departorgcode", departorgCode);
        jsonObject.put("departdate", departDate);
        jsonObject.put("schedulecode", scheduleCode);
        HashMap<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("uid", uid);
        paramMap.put("data", RsaUtil.encrypt(jsonObject.toJSONString()));
        paramMap.put("md5", MD5Util.MD5Encode(jsonObject.toJSONString(), null));

        String departorgs = HttpRequest
                .post(url + "/queryscheduleseatsdetail")
                .form(paramMap)
                .execute()
                .body();
        JSONObject jsonObject1 = JSON.parseObject(departorgs);
        String data = RsaUtil.desEncrypt(jsonObject1.getString("data"));
        JSONObject opseatdetails = JSON.parseObject(data);
        String rescode = opseatdetails.getString("rescode");
        if (TicketUtil.RESCODE.equals(rescode)) {
            CytSeatDetails cytSeatDetails = new CytSeatDetails();
            cytSeatDetails.setDepartorgCode(departorgCode);
            cytSeatDetails.setDepartDate(DateUtil.parse(departDate));
            cytSeatDetails.setScheduleCode(scheduleCode);
            cytSeatDetails.setOpseatDetails(opseatdetails.getString("opseatdetails"));
            if (baseMapper.selectCount(new QueryWrapper<CytSeatDetails>()
                    .eq("departorg_code", departorgCode).eq("depart_date", departDate).eq("schedule_code", scheduleCode)) > 0) {
                baseMapper.update(cytSeatDetails, new QueryWrapper<CytSeatDetails>()
                        .eq("departorg_code", departorgCode).eq("depart_date", departDate).eq("schedule_code", scheduleCode));
            } else {
                baseMapper.insert(cytSeatDetails);
            }
            JSONArray mm = JSONUtil.parseArray(opseatdetails.getString("opseatdetails"));
            redisUtil.hset("letour-tickets-getSeatDetails", departorgCode + departDate + scheduleCode, mm);
            log.info("座位信息更新成功--" + departorgCode);
            return Result.OK(mm);
        } else {
            return Result.error(opseatdetails.getString("resmsg"));
        }
    }

    @Override
    public Result<?> getSeatDetails(String departorgCode, String departDate, String scheduleCode) {
        if (redisUtil.hHasKey("letour-tickets-getSeatDetails", departorgCode + departDate + scheduleCode)) {
            return Result.OK(redisUtil.hget("letour-tickets-getSeatDetails", departorgCode + departDate + scheduleCode));
        } else {
            CytSeatDetails cytSeatDetails = baseMapper.selectOne(new QueryWrapper<CytSeatDetails>()
                    .eq("departorg_code", departorgCode)
                    .eq("depart_date", departDate)
                    .eq("schedule_code", scheduleCode));

            if (cytSeatDetails != null) {
                JSONArray mm = JSONUtil.parseArray(cytSeatDetails.getOpseatDetails());
                redisUtil.hset("letour-tickets-getSeatDetails", departorgCode + departDate + scheduleCode, mm, 600);
                return Result.OK(mm);
            } else {
                return updateSeatDetail(departorgCode, departDate, scheduleCode);
            }
        }
    }
}
