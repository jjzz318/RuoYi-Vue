package com.hyrcb.hydp.common.utils.WeChat;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.http.HttpUtils;

public class WxUser {
    private static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=@appid&secret=@secret&code=@code&grant_type=authorization_code";
    private String appid="";
    private String secret="";
    public WxUser(String _appid,String _secret) {
        this.appid = _appid;
        this.secret = _secret;
    }
    public String getOpenId(String code){
        String jsonResult=this.getAccessToken(code);
        JSONObject jsonObject = JSONUtil.parseObj(jsonResult);
        String openid = jsonObject.getStr("openid");
        return openid;
    }
    public String getAccessToken(String code){
        System.out.println(appid);
        url = url.replace("@appid", appid).replace("@secret",secret).replace("@code",code);
        String[] tmpStr=url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        return ret;
    }
}
