package org.jeecg.modules.handler;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.buslist.service.ICytBuslistService;
import org.jeecg.modules.station.service.ICytEndStationService;
import org.jeecg.modules.station.service.ICytStartStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TicketJobHandler
 * @Description TODO
 * @Author 刘杨刚/Microdream
 * @Date 2021/1/18
 * @Version 1.0
 */
@Component
@Slf4j
public class TicketJobHandler {

    @Autowired
    private ICytStartStationService cytStartStationService;
    @Autowired
    private ICytEndStationService cytEndStationService;
    @Autowired
    private ICytBuslistService cytBuslistService;


    /**
     * 出发站定时任务
     *
     * @param params
     * @return
     */
    @XxlJob(value = "xxlUpdateStartStation")
    public ReturnT<String> xxlUpdateStartStation(String params) {
        Result<?> msg = cytStartStationService.updateStartStation();
        log.info("出发站定时任务：" + msg.getMessage());
        return ReturnT.SUCCESS;
    }

    /**
     * 到达站定时任务
     *
     * @param params
     * @return
     */
    @XxlJob(value = "xxlUpdateEndStation")
    public ReturnT<String> xxlUpdateEndStation(String params) {
        Result<?> msg = cytEndStationService.updateEndStation();
        log.info("到达站定时任务：" + msg.getMessage());
        return ReturnT.SUCCESS;
    }

    /**
     * 班次列表定时任务---今天
     *
     * @param params
     * @return
     */
    @XxlJob(value = "xxlUpdateBusListToday")
    public ReturnT<String> xxlUpdateBusListToday(String params) {
        log.info("班次列表定时任务：");
        if ("".equals(params)) {
            cytBuslistService.updateBusList(DateUtil.today());
        } else {
            cytBuslistService.updateBusList(params);
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 班次列表定时任务---后14天
     *
     * @param params
     * @return
     */
    @XxlJob(value = "xxlUpdateBusListTo")
    public ReturnT<String> xxlUpdateBusListTo(String params) {
        log.info("班次列表定时任务：");
        for (int i = 0; i < 14; i++) {
            String time = DateUtil.formatDate(DateUtil.offsetDay(DateUtil.date(), i + 1));
            cytBuslistService.updateBusList(time);
        }
        return ReturnT.SUCCESS;
    }
}
