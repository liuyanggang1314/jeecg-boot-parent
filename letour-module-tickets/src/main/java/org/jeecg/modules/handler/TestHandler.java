package org.jeecg.modules.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.buslist.service.ICytBuslistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestHandler
 * @Description TODO
 * @Author 刘杨刚/Microdream
 * @Date 2021/1/22
 * @Version 1.0
 */
@Component
@Slf4j
public class TestHandler {
    @Autowired
    private ICytBuslistService cytBuslistService;

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void test123() {
        log.info("定时任务--班次列表、座位--");
        for (int i = 0; i < 15; i++) {
            String time = DateUtil.formatDate(DateUtil.offsetDay(DateUtil.date(), i));
            ThreadUtil.execute(() -> {
                cytBuslistService.updateBusList(time);
            });
        }
    }
}
