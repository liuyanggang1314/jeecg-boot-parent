package org.jeecg.modules.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.insurance.entity.CytInsurancePrice;
import org.jeecg.modules.insurance.mapper.CytInsurancePriceMapper;
import org.jeecg.modules.insurance.service.ICytInsurancePriceService;
import org.springframework.stereotype.Service;

/**
 * @Description: 保险信息配置
 * @Author: 刘杨刚
 * @Date:   2021-01-14
 * @Version: V1.0
 */
@Service
@DS("ticket")
public class CytInsurancePriceServiceImpl extends ServiceImpl<CytInsurancePriceMapper, CytInsurancePrice> implements ICytInsurancePriceService {

}
