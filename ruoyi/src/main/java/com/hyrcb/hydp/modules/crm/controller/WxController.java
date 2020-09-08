package com.hyrcb.hydp.modules.crm.controller;


import com.hyrcb.hydp.common.utils.QrCodeUtil;
import com.hyrcb.hydp.common.utils.WeChat.JsApiBen;
import com.hyrcb.hydp.common.utils.WeChat.WxUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/")
public class WxController extends BaseController {
    /**
     * 获取在线申请清单详细信息
     */
    @PostMapping("getJsApiSignature")
    public AjaxResult getJsApiSignature(@RequestBody String url) {
        WxUtil wxUtil = new WxUtil();
        JsApiBen jsApiBen = new JsApiBen();
        jsApiBen.setAppId("wx002f85d12393d609");
        jsApiBen.setNonceStr("Wm3WZYTPz0wzccnW");
        jsApiBen.setSignature(wxUtil.getJsApiSignature(url));
        jsApiBen.setTimestamp(wxUtil.getTimestamp());
        try {
            String path= RuoYiConfig.getProfile()+"\\shop\\";
            String staffCode=url.split("=")[1];
            QrCodeUtil.createQRCode(url,path,staffCode);
        }catch (Exception e){

        }

        return AjaxResult.success(jsApiBen);
    }
}
