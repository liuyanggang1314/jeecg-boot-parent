package org.jeecg.modules.station.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.boot.starter.rabbitmq.client.RabbitMqClient;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.base.BaseMap;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.station.entity.CytStartStation;
import org.jeecg.modules.station.mapper.CytStartStationMapper;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 出发站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytStartStationServiceImpl extends ServiceImpl<CytStartStationMapper, CytStartStation> implements ICytStartStationService {
    @Value("${letour.uid}")
    private String uid;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RabbitMqClient rabbitMqClient;

    @Override
    @Transactional
    public Result<String> updateStartStation() {
        Result<String> result = new Result<String>();
        String departorgs = HttpRequest
                .get("http://39.130.135.98:9011/openapi/2.0/departorgs?uid=" + uid + "&start=0&count=10000")
                .execute()
                .body();
        JSONObject jsonObject = JSON.parseObject(departorgs);
        if ("0000".equals(jsonObject.getString("rescode"))) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                CytStartStation cytStartStation = new CytStartStation();
                String departorgName = jsonArray.getJSONObject(i).getString("departorgname");
                String departorgCode = jsonArray.getJSONObject(i).getString("departorgcode");
                String departcityId = jsonArray.getJSONObject(i).getString("departcityid");
                String city = jsonArray.getJSONObject(i).getString("city");
                cytStartStation.setDepartorgName(departorgName);
                cytStartStation.setDepartorgCode(departorgCode);
                cytStartStation.setDepartcityId(departcityId);
                cytStartStation.setCity(city);
                cytStartStation.setNameJianpin(PinyinUtil.getFirstLetter(city, ""));
                cytStartStation.setIsCanSell(1);
                baseMapper.delete(new QueryWrapper<CytStartStation>().eq("departorg_name", departorgName));
                baseMapper.insert(cytStartStation);
            }
            BaseMap map = new BaseMap();
            map.put("name", "letour-tickets-getStartStations");
            rabbitMqClient.sendMessage("delRedisData", map, 10000);
            return result.success("更新成功");
        } else {
            result.error500(jsonObject.getString("resmsg"));
            return result;
        }
    }
}
