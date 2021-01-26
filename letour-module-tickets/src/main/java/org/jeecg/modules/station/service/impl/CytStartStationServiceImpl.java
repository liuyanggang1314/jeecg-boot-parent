package org.jeecg.modules.station.service.impl;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.station.entity.CytStartStation;
import org.jeecg.modules.station.mapper.CytStartStationMapper;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.jeecg.modules.utils.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 出发站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Slf4j
@Service
@DS("ticket")
public class CytStartStationServiceImpl extends ServiceImpl<CytStartStationMapper, CytStartStation> implements ICytStartStationService {
    @Value("${letour.uid}")
    private String uid;
    @Value("${letour.url}")
    private String url;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Result<?> updateStartStation() {
        String departorgs = HttpRequest
                .get(url + "/departorgs?uid=" + uid + "&start=0&count=10000")
                .execute()
                .body();
        JSONObject jsonObject = JSON.parseObject(departorgs);
        String rescode = jsonObject.getString("rescode");
        if (TicketUtil.RESCODE.equals(rescode)) {
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
                if (baseMapper.selectCount(new QueryWrapper<CytStartStation>()
                        .eq("departorg_name", departorgName)
                        .eq("departorg_code", departorgCode)) > 0) {
                    baseMapper.update(cytStartStation, new QueryWrapper<CytStartStation>()
                            .eq("departorg_name", departorgName)
                            .eq("departorg_code", departorgCode));
                } else {
                    cytStartStation.setIsCanSell(1);
                    baseMapper.insert(cytStartStation);
                }
            }
            redisUtil.del(TicketUtil.STARTSTATIONS);
            Result<?> result = getStartStations();
            log.info("出发站数据更新成功");
            return result;
        } else {
            return Result.error(jsonObject.getString("resmsg"));
        }
    }

    @Override
    public Result<?> getStartStations() {
        if (redisUtil.hasKey(TicketUtil.STARTSTATIONS)) {
            return Result.OK(redisUtil.get(TicketUtil.STARTSTATIONS));
        } else {
            List<CytStartStation> cytStartStations = baseMapper.selectList(new QueryWrapper<CytStartStation>()
                    .eq("is_can_sell", 1)
                    .groupBy("city")
                    .select("departcity_id", "city", "CHAR(INTERVAL(CONV(HEX(left(convert( city using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));

            List<CytStartStation> pyList = baseMapper.selectList(new QueryWrapper<CytStartStation>()
                    .groupBy("py")
                    .select("CHAR(INTERVAL(CONV(HEX(left(convert( city using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));
            Map<String, Object> map = new LinkedHashMap<>();
            for (CytStartStation py : pyList) {
                List<CytStartStation> list = new ArrayList<>();
                for (CytStartStation cytStartStation : cytStartStations) {
                    if (py.getPy().equals(cytStartStation.getPy())) {
                        list.add(cytStartStation);
                    }
                }
                map.put(py.getPy(), list);
            }
            if (cytStartStations.size() > 0) {
                redisUtil.set(TicketUtil.STARTSTATIONS, map);
                return Result.OK(map);
            } else {
                return updateStartStation();
            }
        }
    }
}
