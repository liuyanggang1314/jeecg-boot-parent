package org.jeecg.modules.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.insurance.entity.CytInsuranceInfo;
import org.jeecg.modules.insurance.mapper.CytInsuranceInfoMapper;
import org.jeecg.modules.insurance.service.ICytInsuranceInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 保险订单
 * @Author: 刘杨刚
 * @Date:   2021-01-14
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytInsuranceInfoServiceImpl extends ServiceImpl<CytInsuranceInfoMapper, CytInsuranceInfo> implements ICytInsuranceInfoService {

}
