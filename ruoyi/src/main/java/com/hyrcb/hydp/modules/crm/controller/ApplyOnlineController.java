package com.hyrcb.hydp.modules.crm.controller;


import com.hyrcb.hydp.modules.crm.service.IApplyOnlineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crm/ApplyOnline" )
public class ApplyOnlineController extends BaseController {
    @Autowired
    private IApplyOnlineService iApplyOnlineService;

    @PostMapping("/loan")
    public String loan(@RequestBody Map map){
        return "測試";
    }
    @GetMapping("/sendVerifyCode")
    public AjaxResult sendVerifyCode(String phone){
        iApplyOnlineService.sendVerifyCode(phone);

        return AjaxResult.success();
    }
}
