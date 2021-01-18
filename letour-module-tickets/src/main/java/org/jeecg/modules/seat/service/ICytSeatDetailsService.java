package org.jeecg.modules.seat.service;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.seat.entity.CytSeatDetails;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param departDate 发车日期
     * @param scheduleCode 班次代码
     * @return
     */
    Result<String> updateSeatDetail(String departorgCode, String departDate, String scheduleCode);
}
