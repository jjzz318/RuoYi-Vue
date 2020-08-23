package com.hyrcb.hydp.modules.crm.controller;


import com.hyrcb.hydp.common.utils.RandomUtil;
import com.hyrcb.hydp.common.utils.SMSUtil;
import com.hyrcb.hydp.modules.crm.service.IApplyOnlineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crm/ApplyOnline" )
public class ApplyOnlineController extends BaseController {


    @PostMapping("/loan")
    public String loan(@RequestBody Map map){
        return "測試";
    }
    @GetMapping("/sendVerifyCode")
    public AjaxResult sendVerifyCode(String phone){
        String code = RandomUtil.randomNumber(6);
        SMSUtil.sendSMS(phone,code);
        Record

        return AjaxResult.success();
    }
}
