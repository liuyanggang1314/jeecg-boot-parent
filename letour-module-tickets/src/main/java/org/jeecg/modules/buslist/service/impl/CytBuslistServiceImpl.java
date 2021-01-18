package org.jeecg.modules.buslist.service.impl;

import cn.hutool.core.date.DateUtil;
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
import org.jeecg.modules.buslist.entity.CytBuslist;
import org.jeecg.modules.buslist.mapper.CytBuslistMapper;
import org.jeecg.modules.buslist.service.ICytBuslistService;
import org.jeecg.modules.station.entity.CytEndStation;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 班次列表
 * @Author: 刘杨刚
 * @Date: 2021-01-11
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytBuslistServiceImpl extends ServiceImpl<CytBuslistMapper, CytBuslist> implements ICytBuslistService {
    @Value("${letour.uid}")
    private String uid;
    @Autowired
    private RabbitMqClient rabbitMqClient;
    @Autowired
    private ICytEndStationService cytEndStationService;

    @Override
    @Transactional
    public Result<String> updateBusList(String departDate) {
        Result<String> result = new Result<String>();
        List<CytEndStation> cytEndStations = cytEndStationService.list(new QueryWrapper<CytEndStation>().eq("is_can_sell", 1));
        for (CytEndStation cytEndStation : cytEndStations) {
            String departorgs = HttpRequest
                    .post("http://39.130.135.98:9011/openapi/2.0/realtimeschedule?uid=" + uid +
                            "&departorgcode=" + cytEndStation.getOrgcode() + "&departdate=" + departDate + "&reachstationname=" + cytEndStation.getName())
                    .execute()
                    .body();
            JSONObject jsonObject = JSON.parseObject(departorgs);
            if ("0000".equals(jsonObject.getString("rescode"))) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.size(); i++) {
                    Integer hashid = jsonArray.getJSONObject(i).getInteger("hashid");
                    String departcityname = jsonArray.getJSONObject(i).getString("departcityname");
                    String departprovince = jsonArray.getJSONObject(i).getString("departprovince");
                    String departorgcode = jsonArray.getJSONObject(i).getString("departorgcode");
                    String schedulecode = jsonArray.getJSONObject(i).getString("schedulecode");
                    String departorgname = jsonArray.getJSONObject(i).getString("departorgname");
                    String departtime = jsonArray.getJSONObject(i).getString("departtime");
                    String endtime = jsonArray.getJSONObject(i).getString("endtime");
                    String seattype = jsonArray.getJSONObject(i).getString("seattype");
                    Integer seatnumber = jsonArray.getJSONObject(i).getInteger("seatnumber");
                    Integer residualnumber = jsonArray.getJSONObject(i).getInteger("residualnumber");
                    Integer sellednumber = jsonArray.getJSONObject(i).getInteger("sellednumber");
                    Integer remainkidseats = jsonArray.getJSONObject(i).getInteger("remainkidseats");
                    Integer isaddschedule = jsonArray.getJSONObject(i).getInteger("isaddschedule");
                    Integer islineschedule = jsonArray.getJSONObject(i).getInteger("islineschedule");
                    Double fullprice = jsonArray.getJSONObject(i).getDouble("fullprice");
                    Double discountprice = jsonArray.getJSONObject(i).getDouble("discountprice");
                    Double studentprice = jsonArray.getJSONObject(i).getDouble("studentprice");
                    Double halfprice = jsonArray.getJSONObject(i).getDouble("halfprice");
                    Double rebateprice = jsonArray.getJSONObject(i).getDouble("rebateprice");
                    Double returnprice = jsonArray.getJSONObject(i).getDouble("returnprice");
                    Double tripprice = jsonArray.getJSONObject(i).getDouble("tripprice");
                    String reachstationcode = jsonArray.getJSONObject(i).getString("reachstationcode");
                    String reachstationname = jsonArray.getJSONObject(i).getString("reachstationname");
                    String reachcityname = jsonArray.getJSONObject(i).getString("reachcityname");
                    String reachprovince = jsonArray.getJSONObject(i).getString("reachprovince");
                    String endstationcode = jsonArray.getJSONObject(i).getString("endstationcode");
                    String endstationname = jsonArray.getJSONObject(i).getString("endstationname");
                    Integer runtime = jsonArray.getJSONObject(i).getInteger("runtime");
                    Integer rundistance = jsonArray.getJSONObject(i).getInteger("rundistance");
                    String vehicletype = jsonArray.getJSONObject(i).getString("vehicletype");
                    String vehicleno = jsonArray.getJSONObject(i).getString("vehicleno");
                    String routcode = jsonArray.getJSONObject(i).getString("routcode");
                    String routname = jsonArray.getJSONObject(i).getString("routname");
                    String busrank = jsonArray.getJSONObject(i).getString("busrank");
                    String busbrand = jsonArray.getJSONObject(i).getString("busbrand");
                    Integer iscansell = jsonArray.getJSONObject(i).getInteger("iscansell");
                    String ticketentrance = jsonArray.getJSONObject(i).getString("ticketentrance");
                    String waitingroom = jsonArray.getJSONObject(i).getString("waitingroom");
                    String starttime = jsonArray.getJSONObject(i).getString("starttime");
                    String unitname = jsonArray.getJSONObject(i).getString("unitname");


                    CytBuslist cytBuslist = new CytBuslist();
                    cytBuslist.setHashId(hashid);
                    cytBuslist.setDepartCityName(departcityname);
                    cytBuslist.setDepartProvince(departprovince);
                    cytBuslist.setDepartorgCode(departorgcode);
                    cytBuslist.setScheduleCode(schedulecode);
                    cytBuslist.setDepartorgName(departorgname);
                    cytBuslist.setDepartTime(departtime);
                    cytBuslist.setEndTime(endtime);
                    cytBuslist.setSeatType(seattype);
                    cytBuslist.setSeatNumber(seatnumber);
                    cytBuslist.setResidualNumber(residualnumber);
                    cytBuslist.setSelledNumber(sellednumber);
                    cytBuslist.setRemainKidSeats(remainkidseats);
                    cytBuslist.setIsAddSchedule(isaddschedule);
                    cytBuslist.setIsLineSchedule(islineschedule);
                    cytBuslist.setFullPrice(fullprice);
                    cytBuslist.setDiscountPrice(discountprice);
                    cytBuslist.setStudentPrice(studentprice);
                    cytBuslist.setHalfPrice(halfprice);
                    cytBuslist.setRebatePrice(rebateprice);
                    cytBuslist.setReturnPrice(returnprice);
                    cytBuslist.setTripPrice(tripprice);
                    cytBuslist.setReachStationCode(reachstationcode);
                    cytBuslist.setReachStationName(reachstationname);
                    cytBuslist.setReachCityName(reachcityname);
                    cytBuslist.setReachProvince(reachprovince);
                    cytBuslist.setEndStationCode(endstationcode);
                    cytBuslist.setEndStationName(endstationname);
                    cytBuslist.setRunTime(runtime);
                    cytBuslist.setRunDistance(rundistance);
                    cytBuslist.setVehicleType(vehicletype);
                    cytBuslist.setVehicleNo(vehicleno);
                    cytBuslist.setRoutCode(routcode);
                    cytBuslist.setRoutName(routname);
                    cytBuslist.setBusRank(busrank);
                    cytBuslist.setBusBrand(busbrand);
                    cytBuslist.setIsCanSell(iscansell);
                    cytBuslist.setTickeTentrance(ticketentrance);
                    cytBuslist.setWaitingRoom(waitingroom);
                    cytBuslist.setStartTime(starttime);
                    cytBuslist.setUnitName(unitname);
                    cytBuslist.setDepartcityId(cytEndStation.getDepartcityId());
                    cytBuslist.setBuslistTime(DateUtil.parse(departDate));

                    if (baseMapper.selectCount(new QueryWrapper<CytBuslist>()
                            .eq("hash_id", hashid)) > 0) {
                        baseMapper.update(cytBuslist, new QueryWrapper<CytBuslist>()
                                .eq("hash_id", hashid));
                    } else {
                        baseMapper.insert(cytBuslist);
                    }
                }
            }
        }
        rabbitMqClient.sendMessage("delRedisData", new BaseMap().put("name", "letour-tickets-getBusList"), 5000);
        return result.success("更新成功");
    }
}
