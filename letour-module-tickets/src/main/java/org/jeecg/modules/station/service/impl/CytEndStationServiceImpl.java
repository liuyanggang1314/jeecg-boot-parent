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
import org.jeecg.modules.station.entity.CytEndStation;
import org.jeecg.modules.station.entity.CytStartStation;
import org.jeecg.modules.station.mapper.CytEndStationMapper;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.jeecg.modules.utils.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Slf4j
@Service
@DS("ticket")
public class CytEndStationServiceImpl extends ServiceImpl<CytEndStationMapper, CytEndStation> implements ICytEndStationService {
    @Value("${letour.uid}")
    private String uid;
    @Value("${letour.url}")
    private String url;
    @Autowired
    private ICytStartStationService cytStartStationService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result<?> updateEndStation() {
        return allReachStations(null);
    }

    /**
     * 获取盛威到达站数据
     * @param departcityId
     * @return
     */
    public Result<?> allReachStations(String departcityId) {
        String departorgs = HttpRequest
                .get(url + "/allreachstations?uid=" + uid + "&start=0&count=10000")
                .execute()
                .body();
        JSONObject jsonObject = JSON.parseObject(departorgs);
        String rescode = jsonObject.getString("rescode");
        if (TicketUtil.RESCODE.equals(rescode)) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                CytEndStation cytEndStation = new CytEndStation();
                String orgcode = jsonArray.getJSONObject(i).getString("orgcode");
                String reachstationcode = jsonArray.getJSONObject(i).getString("reachstationcode");
                String name = jsonArray.getJSONObject(i).getString("name");
                String province = jsonArray.getJSONObject(i).getString("province");
                String nameJianpin = jsonArray.getJSONObject(i).getString("name_jianpin");
                String city = jsonArray.getJSONObject(i).getString("city");

                CytStartStation cytStartStation = cytStartStationService.getOne(new QueryWrapper<CytStartStation>()
                        .eq("departorg_code", orgcode));
                cytEndStation.setOrgcode(orgcode);
                cytEndStation.setName(name);
                cytEndStation.setProvince(province);
                cytEndStation.setNameJianpin(PinyinUtil.getFirstLetter(name, ""));
                cytEndStation.setReachstationcode(reachstationcode);
                cytEndStation.setCity(city);
                if (cytStartStation != null) {
                    cytEndStation.setDepartorgName(cytStartStation.getDepartorgName());
                    cytEndStation.setOrgname(cytStartStation.getCity());
                    cytEndStation.setDepartcityId(cytStartStation.getDepartcityId());
                }

                if (baseMapper.selectCount(new QueryWrapper<CytEndStation>()
                        .eq("orgcode", orgcode)
                        .eq("reachstationcode", reachstationcode)) > 0) {
                    baseMapper.update(cytEndStation, new QueryWrapper<CytEndStation>()
                            .eq("orgcode", orgcode)
                            .eq("reachstationcode", reachstationcode));
                } else {
                    cytEndStation.setIsCanSell(0);
                    baseMapper.insert(cytEndStation);
                }
            }
            log.info("到达站数据更新成功");
            List<CytStartStation> cytStartStations = cytStartStationService.list(new QueryWrapper<CytStartStation>()
                    .eq("is_can_sell", 1)
                    .groupBy("city"));
            for (CytStartStation cytStartStation : cytStartStations) {
                setListListCytEndStation(cytStartStation.getDepartcityId());
            }

            if (departcityId == null){
                return Result.OK("更新成功");
            }else {
                return setListListCytEndStation(departcityId);
            }

        } else {
            return Result.error(jsonObject.getString("resmsg"));
        }
    }

    @Override
    public Result<?> getEndStations(String departcityId) {
        log.info(departcityId);
        if (redisUtil.hHasKey(TicketUtil.ENDSTATIONS, departcityId)) {
            return Result.OK(redisUtil.hget(TicketUtil.ENDSTATIONS, departcityId));
        } else {
            return setListListCytEndStation(departcityId);
        }
    }

    public Result<?> setListListCytEndStation(String departcityId) {
        List<CytEndStation> cytStartStations = baseMapper.selectList(new QueryWrapper<CytEndStation>()
                .eq("is_can_sell", 1)
                .eq("departcity_id", departcityId)
                .groupBy("name")
                .orderByAsc("name_jianpin")
                .select("orgname", "name", "departcity_id", "CHAR(INTERVAL(CONV(HEX(left(convert( name using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));

        List<CytEndStation> pyList = baseMapper.selectList(new QueryWrapper<CytEndStation>()
                .groupBy("py")
                .select("CHAR(INTERVAL(CONV(HEX(left(convert( name using gbk ) collate gbk_chinese_ci,1)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCDDA,0xCDDA,0xCEF4,0xD1B9,0xD4D1)+64) as py"));
        Map<String, Object> map = new LinkedHashMap<>();
        for (CytEndStation py : pyList) {
            List<CytEndStation> list = new ArrayList<>();
            for (CytEndStation cytEndStation : cytStartStations) {
                if (py.getPy().equals(cytEndStation.getPy())) {
                    list.add(cytEndStation);
                }
            }
            map.put(py.getPy(), list);
        }
        if (cytStartStations.size() > 0) {
            redisUtil.hdel(TicketUtil.ENDSTATIONS, departcityId);
            redisUtil.hset(TicketUtil.ENDSTATIONS, departcityId, map);
            return Result.OK(map);
        } else {
            return Result.error("数据为空");
        }
    }
}
