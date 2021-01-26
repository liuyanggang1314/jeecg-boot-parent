package org.jeecg.modules.seat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.seat.entity.CytSeatDetails;

/**
 * @Description: 座位信息
 * @Author: 刘杨刚
 * @Date: 2021-01-14
 * @Version: V1.0
 */
public interface ICytSeatDetailsService extends IService<CytSeatDetails> {
    /**
     * 更新座位信息--盛威
     *
     * @param departorgCode 发车站编码
     * @param departDate    发车日期
     * @param scheduleCode  班次代码
     * @return
     */
    Result<?> updateSeatDetail(String departorgCode, String departDate, String scheduleCode);

    Result<?> getSeatDetails(String departorgCode, String departDate, String scheduleCode);
}
