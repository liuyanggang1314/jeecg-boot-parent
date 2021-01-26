package org.jeecg.modules.station.service;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.station.entity.CytStartStation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 出发站
 * @Author: 刘杨刚
 * @Date: 2021-01-08
 * @Version: V1.0
 */
public interface ICytStartStationService extends IService<CytStartStation> {

    /**
     * 更新出发站数据--盛威
     * @return
     */
    Result<?> updateStartStation();

    /**
     * 获取出发站数据
     * @return
     */
    Result<?> getStartStations();
}
