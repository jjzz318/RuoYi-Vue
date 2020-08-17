package com.hyrcb.hydp.modules.crm.controller;

import com.ruoyi.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crm/ApplyOnline" )
public class ApplyOnlineController extends BaseController {
    @PostMapping("/loan")
    public String loan(@RequestBody Map map){
        return "測試";
    }
}
