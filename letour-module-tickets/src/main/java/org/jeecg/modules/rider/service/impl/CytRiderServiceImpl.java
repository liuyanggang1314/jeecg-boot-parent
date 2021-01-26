package org.jeecg.modules.rider.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.rider.entity.CytRider;
import org.jeecg.modules.rider.mapper.CytRiderMapper;
import org.jeecg.modules.rider.service.ICytRiderService;
import org.springframework.stereotype.Service;

/**
 * @Description: 乘车人列表
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Service
@DS("ticket")
public class CytRiderServiceImpl extends ServiceImpl<CytRiderMapper, CytRider> implements ICytRiderService {

}
