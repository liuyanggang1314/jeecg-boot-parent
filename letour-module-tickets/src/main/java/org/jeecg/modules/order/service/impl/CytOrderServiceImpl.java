package org.jeecg.modules.order.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.order.entity.CytOrder;
import org.jeecg.modules.order.entity.CytTickets;
import org.jeecg.modules.order.mapper.CytOrderMapper;
import org.jeecg.modules.order.mapper.CytTicketsMapper;
import org.jeecg.modules.order.service.ICytOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单信息
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Service
@DS("ticket")
public class CytOrderServiceImpl extends ServiceImpl<CytOrderMapper, CytOrder> implements ICytOrderService {

	@Autowired
	private CytOrderMapper cytOrderMapper;
	@Autowired
	private CytTicketsMapper cytTicketsMapper;
	
	@Override
	@Transactional
	public void saveMain(CytOrder cytOrder, List<CytTickets> cytTicketsList) {
		cytOrderMapper.insert(cytOrder);
		if(cytTicketsList!=null && cytTicketsList.size()>0) {
			for(CytTickets entity:cytTicketsList) {
				//外键设置
				entity.setOrderId(cytOrder.getId());
				cytTicketsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(CytOrder cytOrder,List<CytTickets> cytTicketsList) {
		cytOrderMapper.updateById(cytOrder);
		
		//1.先删除子表数据
		cytTicketsMapper.deleteByMainId(cytOrder.getId());
		
		//2.子表数据重新插入
		if(cytTicketsList!=null && cytTicketsList.size()>0) {
			for(CytTickets entity:cytTicketsList) {
				//外键设置
				entity.setOrderId(cytOrder.getId());
				cytTicketsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		cytTicketsMapper.deleteByMainId(id);
		cytOrderMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			cytTicketsMapper.deleteByMainId(id.toString());
			cytOrderMapper.deleteById(id);
		}
	}
	
}
