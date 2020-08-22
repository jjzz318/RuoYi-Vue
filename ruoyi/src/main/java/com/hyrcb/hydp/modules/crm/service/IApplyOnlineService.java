package com.hyrcb.hydp.modules.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyrcb.hydp.modules.crm.domain.Product;
import com.ruoyi.framework.web.domain.AjaxResult;

public interface IApplyOnlineService extends IService <Product> {
    public AjaxResult sendVerifyCode(String phone);
}
