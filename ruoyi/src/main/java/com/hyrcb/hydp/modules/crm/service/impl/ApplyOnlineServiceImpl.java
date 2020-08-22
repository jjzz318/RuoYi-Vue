package com.hyrcb.hydp.modules.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.common.utils.RandomUtil;
import com.hyrcb.hydp.common.utils.SMSUtil;
import com.hyrcb.hydp.modules.crm.domain.Product;
import com.hyrcb.hydp.modules.crm.mapper.ApplyOnlineMapper;
import com.hyrcb.hydp.modules.crm.mapper.ProductMapper;
import com.hyrcb.hydp.modules.crm.service.IApplyOnlineService;
import com.ruoyi.framework.web.domain.AjaxResult;

public class ApplyOnlineServiceImpl extends ServiceImpl<ApplyOnlineMapper, Product> implements IApplyOnlineService {
    @Override
    public AjaxResult sendVerifyCode(String phone){
        String code = RandomUtil.randomNumber(6);
        SMSUtil.sendSMS(phone,code);

        return AjaxResult.success();
    }
}
