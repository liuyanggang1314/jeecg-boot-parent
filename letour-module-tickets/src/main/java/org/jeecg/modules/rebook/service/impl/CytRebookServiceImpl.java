package org.jeecg.modules.rebook.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.rebook.entity.CytRebook;
import org.jeecg.modules.rebook.mapper.CytRebookMapper;
import org.jeecg.modules.rebook.service.ICytRebookService;
import org.springframework.stereotype.Service;

/**
 * @Description: 改签记录
 * @Author: 刘杨刚
 * @Date:   2021-01-15
 * @Version: V1.0
 */
@Service
@DS("ticketapi")
public class CytRebookServiceImpl extends ServiceImpl<CytRebookMapper, CytRebook> implements ICytRebookService {

}
