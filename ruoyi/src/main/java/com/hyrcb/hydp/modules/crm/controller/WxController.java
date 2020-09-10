package com.hyrcb.hydp.modules.crm.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hyrcb.hydp.common.utils.QrCodeUtil;
import com.hyrcb.hydp.common.utils.WeChat.AuthorizeBen;
import com.hyrcb.hydp.common.utils.WeChat.JsApiBen;
import com.hyrcb.hydp.common.utils.WeChat.WxUserBen;
import com.hyrcb.hydp.common.utils.WeChat.WxUtil;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wx/")
public class WxController extends BaseController {
    /**
     * 获取在线申请清单详细信息
     */
    @PostMapping("getJsApiSignature")
    public AjaxResult getJsApiSignature(@RequestBody String url) {
        System.out.println("url="+url);
        WxUtil wxUtil = new WxUtil();
        JsApiBen jsApiBen = new JsApiBen();
        jsApiBen.setAppId("wx002f85d12393d609");
        jsApiBen.setNonceStr("Wm3WZYTPz0wzccnW");
        jsApiBen.setSignature(wxUtil.getJsApiSignature(url));
        jsApiBen.setTimestamp(wxUtil.getTimestamp());
        try {
            String path= RuoYiConfig.getProfile()+"/shop/";
            String staffCode=url.split("=")[1];
            QrCodeUtil.createQRCode(url,path,staffCode);
        }catch (Exception e){

        }

        return AjaxResult.success(jsApiBen);
    }
    @PostMapping("getAuthorizeUrl")
    public String  getAuthorizeUrl(@RequestBody AuthorizeBen authorizeBen){
        System.out.println("进入方法");
        WxUtil wxUtil=new WxUtil();
        String url=wxUtil.getAuthorizeURL(authorizeBen.getRedirectUri(),authorizeBen.getCurrentUrl(),false);
        System.out.println(url);
        return url;
    }
    @GetMapping("redirectAuthorize")
    public RedirectView redirectAuthorize(@RequestParam String code,String state){
        System.out.println("进入回调");
        System.out.println("code="+code);
        System.out.println("state="+state);
        WxUtil wxUtil=new WxUtil();
        WxUserBen wxUserBen = wxUtil.wxUser.getUserInfo(code);
        if(wxUserBen!=null){
            Record record=new Record();
            record.set("open_id",wxUserBen.getOpenId());
            record.set("nickname",wxUserBen.getNickname());
            record.set("sex",wxUserBen.getSex());
            record.set("region",wxUserBen.getRegion());
            record.set("headimgurl",wxUserBen.getHeadimgurl());
            record.set("remark",wxUserBen.getRemark());
            Db.save("crm_wx_user",record);

            Record record2=new Record();
            record2.set("open_id",wxUserBen.getOpenId());
            record2.set("user_action","首次访问");
            record2.set("create_time",new Date());
            Db.save("crm_wx_user_log",record2);
        }
        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl(state+"&openid="+wxUserBen.getOpenId());
        return redirectTarget;
    }
    @GetMapping("getWxUserInfo")
    public AjaxResult getWxUserInfo(@RequestParam String openid,String staffCode){
        System.out.println(openid);
        AjaxResult ajaxResult=new AjaxResult();
        Record record=Db.findFirst("SELECT * from crm_wx_user where open_id=?",openid);
        if(record==null){
            ajaxResult=AjaxResult.success("state","false");
        }else{
            Record record2=new Record();
            record2.set("open_id",openid);
            record2.set("user_action","浏览微店");
            record2.set("staff_code",staffCode);
            record2.set("create_time",new Date());
            Db.save("crm_wx_user_log",record2);
            ajaxResult=AjaxResult.success("state","true");
        }
        return ajaxResult;
    }
    @PostMapping("recordClick")
    public AjaxResult  recordClick(@RequestBody String body){
        System.out.println("进入记录点击方法");
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Record record2=new Record();
        record2.set("open_id",jsonObject.getStr("openId"));
        record2.set("user_action","点击产品");
        record2.set("staff_code",jsonObject.getStr("staffCode"));
        record2.set("product_id",jsonObject.getStr("productId"));
        record2.set("create_time",new Date());
        Db.save("crm_wx_user_log",record2);
        return AjaxResult.success();
    }
}
