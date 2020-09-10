package com.hyrcb.hydp.common.utils.WeChat;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.http.HttpUtils;

public class WxUser {
    private String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=@appid&secret=@secret&code=@code&grant_type=authorization_code";//获取openid的access_token
    private String url2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=@appid&secret=@secret";//获取用户信息的token
    private String appid = "";
    private String secret = "";

    public WxUser(String _appid, String _secret) {
        this.appid = _appid;
        this.secret = _secret;
    }

    public String getOpenId(String code) {
        String jsonResult = this.getAccessToken(code);
        JSONObject jsonObject = JSONUtil.parseObj(jsonResult);
        String openid = jsonObject.getStr("openid");
        return openid;
    }

    public String getAccessToken(String code) {
        System.out.println(appid);
        url = url.replace("@appid", appid).replace("@secret", secret).replace("@code", code);
        String[] tmpStr = url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        return ret;
    }

    /**
     * 获取token用于客户信息的获取
     * @return
     */
    public String getToken() {
        url2 = url2.replace("@appid", appid).replace("@secret", secret);
        String[] tmpStr = url2.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        String access_token = jsonObject.getStr("access_token");
        return access_token;
    }

    public WxUserBen getUserInfo(String code) {
        String _url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=@ACCESS_TOKEN&openid=@OPENID&lang=zh_CN";
        String token = this.getToken();
        System.out.println("token=" + token);
        String openid = this.getOpenId(code);
        _url = _url.replace("@ACCESS_TOKEN", token).replace("@OPENID", openid);
        String[] tmpStr = _url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        System.out.println("useinfo:" + ret);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        WxUserBen wxUserBen=new WxUserBen();
        wxUserBen.setOpenId(openid);
        wxUserBen.setNickname(jsonObject.getStr("nickname"));
        wxUserBen.setSex(jsonObject.getStr("sex"));
        wxUserBen.setRegion(jsonObject.getStr("country")+jsonObject.getStr("province")+jsonObject.getStr("city"));
        wxUserBen.setHeadimgurl(jsonObject.getStr("headimgurl"));
        wxUserBen.setRemark(jsonObject.getStr("remark"));
        return wxUserBen;
    }
}
