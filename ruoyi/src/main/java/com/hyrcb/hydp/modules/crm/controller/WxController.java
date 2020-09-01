package com.hyrcb.hydp.modules.crm.controller;

import com.hyrcb.hydp.common.utils.WeChat.JsApiBen;
import com.hyrcb.hydp.common.utils.WeChat.WxUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/")
public class WxController extends BaseController {
    /**
     * 获取在线申请清单详细信息
     */
    @PostMapping("getJsApiSignature")
    public AjaxResult getJsApiSignature(@RequestBody String url) {
        System.out.println("getJsApiSignature");
        WxUtil wxUtil=new WxUtil();
        JsApiBen jsApiBen=new JsApiBen();
        jsApiBen.setAppId("wx002f85d12393d609");
        jsApiBen.setNonceStr("Wm3WZYTPz0wzccnW");
        jsApiBen.setSignature(wxUtil.getJsApiSignature(url));
        jsApiBen.setTimestamp(wxUtil.getTimestamp());
         return AjaxResult.success(jsApiBen);
    }
}
