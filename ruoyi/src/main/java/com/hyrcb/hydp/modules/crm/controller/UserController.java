package com.hyrcb.hydp.modules.crm.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api("对外用户接口")
@RestController
@RequestMapping("/openApi/user")
public class UserController {
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody Map map) {
        System.out.println(map.get("username"));
        AjaxResult ajaxResult=new AjaxResult().success();
        ajaxResult.put("username",map.get("username"));
        return ajaxResult;
    }
}
