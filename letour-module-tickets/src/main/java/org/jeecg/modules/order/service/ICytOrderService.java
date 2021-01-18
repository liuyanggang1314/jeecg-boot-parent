package org.jeecg.modules.order.service;

import org.jeecg.modules.order.entity.CytTickets;
import org.jeecg.modules.order.entity.CytOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单信息
 * @Author: 刘杨刚
 * @Date: 2021-01-15
 * @Version: V1.0
 */
public interface ICytOrderService extends IService<CytOrder> {

    /**
     * 添加一对多
     */
    public void saveMain(CytOrder cytOrder, List<CytTickets> cytTicketsList);

    /**
     * 修改一对多
     */
    public void updateMain(CytOrder cytOrder, List<CytTickets> cytTicketsList);

    /**
     * 删除一对多
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

}
