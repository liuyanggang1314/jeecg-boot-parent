package org.jeecg.modules.buslist.service.impl;

import cn.hutool.core.date.DateUtil;
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
import org.jeecg.modules.buslist.entity.CytBuslist;
import org.jeecg.modules.buslist.mapper.CytBuslistMapper;
import org.jeecg.modules.buslist.service.ICytBuslistService;
import org.jeecg.modules.seat.service.ICytSeatDetailsService;
import org.jeecg.modules.station.entity.CytEndStation;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.jeecg.modules.utils.TicketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 班次列表
 * @Author: 刘杨刚
 * @Date: 2021-01-11
 * @Version: V1.0
 */
@Slf4j
@Service
@DS("ticket")
public class CytBuslistServiceImpl extends ServiceImpl<CytBuslistMapper, CytBuslist> implements ICytBuslistService {
    @Value("${letour.uid}")
    private String uid;
    @Value("${letour.url}")
    private String url;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICytEndStationService cytEndStationService;
    @Autowired
    private ICytSeatDetailsService cytSeatDetailsService;


    @Override
    public Result<?> updateBusList(String departDate) {
        List<CytEndStation> cytEndStations = cytEndStationService.list(new QueryWrapper<CytEndStation>().eq("is_can_sell", 1));
        for (CytEndStation cytEndStation : cytEndStations) {
            String departorgs = HttpRequest
                    .post(url + "/realtimeschedule?uid=" + uid +
                            "&departorgcode=" + cytEndStation.getOrgcode() + "&departdate=" + departDate + "&reachstationname=" + cytEndStation.getName())
                    .execute()
                    .body();
            JSONObject jsonObject = JSON.parseObject(departorgs);
            String rescode = jsonObject.getString("rescode");
            if (TicketUtil.RESCODE.equals(rescode)) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.size(); i++) {
                    CytBuslist cytBuslist = new CytBuslist();
                    cytBuslist.setHashId(jsonArray.getJSONObject(i).getInteger("hashid"));
                    cytBuslist.setDepartCityName(jsonArray.getJSONObject(i).getString("departcityname"));
                    cytBuslist.setDepartProvince(jsonArray.getJSONObject(i).getString("departprovince"));
                    cytBuslist.setDepartorgCode(jsonArray.getJSONObject(i).getString("departorgcode"));
                    cytBuslist.setScheduleCode(jsonArray.getJSONObject(i).getString("schedulecode"));
                    cytBuslist.setDepartorgName(jsonArray.getJSONObject(i).getString("departorgname"));
                    cytBuslist.setDepartTime(jsonArray.getJSONObject(i).getString("departtime"));
                    cytBuslist.setEndTime(jsonArray.getJSONObject(i).getString("endtime"));
                    cytBuslist.setSeatType(jsonArray.getJSONObject(i).getString("seattype"));
                    cytBuslist.setSeatNumber(jsonArray.getJSONObject(i).getInteger("seatnumber"));
                    cytBuslist.setResidualNumber(jsonArray.getJSONObject(i).getInteger("residualnumber"));
                    cytBuslist.setSelledNumber(jsonArray.getJSONObject(i).getInteger("sellednumber"));
                    cytBuslist.setRemainKidSeats(jsonArray.getJSONObject(i).getInteger("remainkidseats"));
                    cytBuslist.setIsAddSchedule(jsonArray.getJSONObject(i).getInteger("isaddschedule"));
                    cytBuslist.setIsLineSchedule(jsonArray.getJSONObject(i).getInteger("islineschedule"));
                    cytBuslist.setFullPrice(jsonArray.getJSONObject(i).getDouble("fullprice"));
                    cytBuslist.setDiscountPrice(jsonArray.getJSONObject(i).getDouble("discountprice"));
                    cytBuslist.setStudentPrice(jsonArray.getJSONObject(i).getDouble("studentprice"));
                    cytBuslist.setHalfPrice(jsonArray.getJSONObject(i).getDouble("halfprice"));
                    cytBuslist.setRebatePrice(jsonArray.getJSONObject(i).getDouble("rebateprice"));
                    cytBuslist.setReturnPrice(jsonArray.getJSONObject(i).getDouble("returnprice"));
                    cytBuslist.setTripPrice(jsonArray.getJSONObject(i).getDouble("tripprice"));
                    cytBuslist.setReachStationCode(jsonArray.getJSONObject(i).getString("reachstationcode"));
                    cytBuslist.setReachStationName(jsonArray.getJSONObject(i).getString("reachstationname"));
                    cytBuslist.setReachCityName(jsonArray.getJSONObject(i).getString("reachcityname"));
                    cytBuslist.setReachProvince(jsonArray.getJSONObject(i).getString("reachprovince"));
                    cytBuslist.setEndStationCode(jsonArray.getJSONObject(i).getString("endstationcode"));
                    cytBuslist.setEndStationName(jsonArray.getJSONObject(i).getString("endstationname"));
                    cytBuslist.setRunTime(jsonArray.getJSONObject(i).getInteger("runtime"));
                    cytBuslist.setRunDistance(jsonArray.getJSONObject(i).getInteger("rundistance"));
                    cytBuslist.setVehicleType(jsonArray.getJSONObject(i).getString("vehicletype"));
                    cytBuslist.setVehicleNo(jsonArray.getJSONObject(i).getString("vehicleno"));
                    cytBuslist.setRoutCode(jsonArray.getJSONObject(i).getString("routcode"));
                    cytBuslist.setRoutName(jsonArray.getJSONObject(i).getString("routname"));
                    cytBuslist.setBusRank(jsonArray.getJSONObject(i).getString("busrank"));
                    cytBuslist.setBusBrand(jsonArray.getJSONObject(i).getString("busbrand"));
                    cytBuslist.setIsCanSell(jsonArray.getJSONObject(i).getInteger("iscansell"));
                    cytBuslist.setTickeTentrance(jsonArray.getJSONObject(i).getString("ticketentrance"));
                    cytBuslist.setWaitingRoom(jsonArray.getJSONObject(i).getString("waitingroom"));
                    cytBuslist.setStartTime(jsonArray.getJSONObject(i).getString("starttime"));
                    cytBuslist.setUnitName(jsonArray.getJSONObject(i).getString("unitname"));
                    cytBuslist.setDepartcityId(cytEndStation.getDepartcityId());
                    cytBuslist.setBuslistTime(DateUtil.parse(departDate));
                    if (baseMapper.selectCount(new QueryWrapper<CytBuslist>()
                            .eq("hash_id", jsonArray.getJSONObject(i).getInteger("hashid"))) > 0) {
                        baseMapper.update(cytBuslist, new QueryWrapper<CytBuslist>()
                                .eq("hash_id", jsonArray.getJSONObject(i).getInteger("hashid")));
                    } else {
                        baseMapper.insert(cytBuslist);
                    }
                    log.info("班次数据更新成功--" + jsonArray.getJSONObject(i).getString("routname"));
                    cytSeatDetailsService.updateSeatDetail(jsonArray.getJSONObject(i).getString("departorgcode"), departDate, jsonArray.getJSONObject(i).getString("schedulecode"));
                }
            }
        }

        List<CytEndStation> cytEndStations1 = cytEndStationService.list(new QueryWrapper<CytEndStation>().eq("is_can_sell", 1)
                .groupBy("name"));
        for (CytEndStation cytEndStation : cytEndStations1) {
            setBusList(cytEndStation.getDepartcityId(), cytEndStation.getName(), departDate);
        }

        return Result.OK("更新成功");
    }

    @Override
    public Result<?> getBusList(String departcityId, String endStationName, String departDate) {
        if (redisUtil.hHasKey(TicketUtil.BUSLIST, departcityId + endStationName + departDate)) {
            return Result.OK(redisUtil.hget(TicketUtil.BUSLIST, departcityId + endStationName + departDate));
        } else {
            return setBusList(departcityId, endStationName, departDate);
        }
    }

    public Result<?> setBusList(String departcityId, String endStationName, String departDate) {
        QueryWrapper<CytBuslist> queryWrapper = new QueryWrapper<CytBuslist>()
                .eq("departcity_id", departcityId)
                .eq("buslist_time", departDate)
                .eq("is_can_sell", 1)
                .eq("end_station_name", endStationName)
                .orderByAsc("depart_time");
        if (DateUtil.today().equals(departDate)) {
            queryWrapper.ge("depart_time", DateUtil.format(new Date(), "HH:mm"));
        }

        List<CytBuslist> cytStartStations = baseMapper.selectList(queryWrapper);

        if (cytStartStations.size() > 0) {
            redisUtil.hdel(TicketUtil.BUSLIST, departcityId + endStationName + departDate);
            redisUtil.hset(TicketUtil.BUSLIST, departcityId + endStationName + departDate, cytStartStations,600);
            return Result.OK(cytStartStations);
        } else {
            return Result.error("数据为空");
        }
    }
}
