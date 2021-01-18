package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.CytTickets;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 车票信息
 * @Author: 刘杨刚
 * @Date: 2021-01-15
 * @Version: V1.0
 */
public interface ICytTicketsService extends IService<CytTickets> {

    public List<CytTickets> selectByMainId(String mainId);
}
