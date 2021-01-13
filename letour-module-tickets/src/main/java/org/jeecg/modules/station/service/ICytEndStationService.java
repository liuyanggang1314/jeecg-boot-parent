package org.jeecg.modules.station.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.station.entity.CytEndStation;

/**
 * @Description: 到达站
 * @Author: 刘杨刚
 * @Date:   2021-01-08
 * @Version: V1.0
 */
public interface ICytEndStationService extends IService<CytEndStation> {

    /**
     * 更新到达站数据--盛威
     *
     * @return
     */
    Result<String> updateEndStation();

}
