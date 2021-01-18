package org.jeecg.modules.buslist.service;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.buslist.entity.CytBuslist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 班次列表
 * @Author: 刘杨刚
 * @Date: 2021-01-11
 * @Version: V1.0
 */
public interface ICytBuslistService extends IService<CytBuslist> {

    /**
     * 更新班次列表数据--盛威
     *
     * @param departDate
     * @return
     */
    Result<String> updateBusList(String departDate);
}
