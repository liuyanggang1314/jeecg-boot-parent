package org.jeecg.modules.order.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.order.entity.CytTickets;
import org.jeecg.modules.order.mapper.CytTicketsMapper;
import org.jeecg.modules.order.service.ICytTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 车票信息
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Service
@DS("ticket")
public class CytTicketsServiceImpl extends ServiceImpl<CytTicketsMapper, CytTickets> implements ICytTicketsService {
	
	@Autowired
	private CytTicketsMapper cytTicketsMapper;
	
	@Override
	public List<CytTickets> selectByMainId(String mainId) {
		return cytTicketsMapper.selectByMainId(mainId);
	}
}
