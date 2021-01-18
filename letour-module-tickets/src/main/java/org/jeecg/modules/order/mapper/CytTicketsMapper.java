package org.jeecg.modules.order.mapper;

import java.util.List;
import org.jeecg.modules.order.entity.CytTickets;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 车票信息
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
public interface CytTicketsMapper extends BaseMapper<CytTickets> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CytTickets> selectByMainId(@Param("mainId") String mainId);
}
