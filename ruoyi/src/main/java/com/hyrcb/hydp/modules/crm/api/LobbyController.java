package com.hyrcb.hydp.modules.crm.api;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("大堂系统接口")
@RestController
@RequestMapping("/api/crm/lobby")
public class LobbyController extends BaseController {

    @ApiOperation("获取当前大堂客户列表")
    @GetMapping("/getCurrentCustList")
    public AjaxResult getCurrentCustList(){
        List<Record> recordList= Db.find("select * from crm_lobby_customer where state=1");

        return AjaxResult.success(recordList);
    }
}
