package com.hyrcb.hydp.modules.crm.service;

import com.hyrcb.hydp.modules.crm.domain.ApplyOnline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 在线申请清单Service接口
 *
 * @author shenzulun
 * @date 2020-08-23
 */
public interface IApplyOnlineService extends IService<ApplyOnline> {
    public AjaxResult verifyCodeExist(String code);
    public boolean ApplyOnlineExist(String idcardCode, long productId);
}
