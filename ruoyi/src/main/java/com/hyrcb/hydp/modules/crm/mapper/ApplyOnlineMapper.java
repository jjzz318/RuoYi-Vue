package com.hyrcb.hydp.modules.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.framework.web.domain.AjaxResult;

public interface ApplyOnlineMapper extends BaseMapper {
    public AjaxResult sendVerifyCode(String phone);
}
