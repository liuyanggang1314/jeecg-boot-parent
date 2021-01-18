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
import org.jeecg.modules.station.entity.CytEndStation;
import org.jeecg.modules.station.entity.CytStartStation;
import org.jeecg.modules.station.mapper.CytEndStationMapper;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytEndStationServiceImpl extends ServiceImpl<CytEndStationMapper, CytEndStation> implements ICytEndStationService {
    @Value("${letour.uid}")
    private String uid;
    @Autowired
    private ICytStartStationService cytStartStationService;
    @Autowired
    private RabbitMqClient rabbitMqClient;

    @Override
    @Transactional
    public Result<String> updateEndStation() {
        Result<String> result = new Result<String>();
        String departorgs = HttpRequest
                .get("http://39.130.135.98:9011/openapi/2.0/allreachstations?uid=" + uid + "&start=0&count=10000")
                .execute()
                .body();
        JSONObject jsonObject = JSON.parseObject(departorgs);
        if ("0000".equals(jsonObject.getString("rescode"))) {
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
                        .eq("departorg_code", orgcode)
                        .last("limit 1"));
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
            BaseMap map = new BaseMap();
            map.put("name", "letour-tickets-getEndStations");
            rabbitMqClient.sendMessage("delRedisData", map);
            return result.success("更新成功");
        } else {
            return result.error500(jsonObject.getString("resmsg"));
        }
    }
}
