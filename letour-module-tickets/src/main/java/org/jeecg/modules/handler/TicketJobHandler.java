package org.jeecg.modules.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
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
    @XxlJob(value = "demoJob")
    public ReturnT<String> demoJobHandler(String params) {
        log.info("我是定时任务,我执行了...............................");
        return ReturnT.SUCCESS;
    }
}
