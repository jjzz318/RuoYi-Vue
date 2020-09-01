package com.hyrcb.hydp.common.utils.WeChat;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.utils.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class WxUtil {
    //appid
    private String appid = "wx002f85d12393d609";
    //secret
    private String secret = "6df62a1b691b32995c0549fc2c6dc4aa";
    //腾讯微信服务器
    String baseUrl = "https://api.weixin.qq.com/cgi-bin/";
    //鉴权服务器地址
    private static String authorize_uri = "https://open.weixin.qq.com/connect/oauth2/authorize";
    //微信鉴权回调服务器
    //private String callback_uri = "http://cc1212.natappvip.cc/exue/user/weXinAuth";
    private static String accessToken = "";
    public static WxMenu wxMenu = null;
    public static WxUser wxUser = null;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public WxUtil() {
        accessToken = this.getAccessToken();
        wxMenu = new WxMenu(accessToken);
        wxUser = new WxUser(appid, secret);
    }

    public WxUtil(String appid, String secret) {
        accessToken = this.getAccessToken();
        wxMenu = new WxMenu(accessToken);
        wxUser = new WxUser(appid, secret);
    }

    /**
     * token
     *
     * @return
     */
    public String getAccessToken() {
//        WxBen wxBen = CacheKit.get( appid,secret );
//        if (wxBen == null) {
//            System.out.println( "开始缓存7788" );
//            wxBen = new WxBen();
//            String accessToken = this.doGetAccessToken();
//            wxBen.setAppid( appid );
//            wxBen.setSecret( secret );
//            wxBen.setAccessToken( accessToken );
//            CacheKit.put( appid,secret,wxBen );
//        }
//        return wxBen.getAccessToken();
        return this.doGetAccessToken();
    }

    /**
     * 访问微信服务器获取AccessToken
     *
     * @return
     */
    private String doGetAccessToken() {
        String url = baseUrl + "token?grant_type=client_credential&appid=@APPID&secret=@APPSECRET";
        url = url.replace("@APPID", appid);
        url = url.replace("@APPSECRET", secret);
        String[] tmpStr = url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        String accessToken = jsonObject.getStr("access_token");
        return accessToken;
    }

    /**
     * 获取微信JSSDK的Ticket
     *
     * @return
     */
    public String getJsApiTicket() {
        String url = baseUrl + "ticket/getticket?access_token=@ACCESS_TOKEN&type=jsapi";
        url = url.replace("@ACCESS_TOKEN", this.getAccessToken());
        String[] tmpStr = url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        String ticket = jsonObject.getStr("ticket");
        return ticket;
    }

    /**
     * 获取微信JSSDK的签名-Signature
     *
     * @return
     */
    public String getJsApiSignature() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("noncestr", "Wm3WZYTPz0wzccnW");
        params.put("jsapi_ticket", this.getJsApiTicket());
        params.put("timestamp", this.create_timestamp());
        params.put("url", "http://cc1212.natappvip.cc/crm/shop/home?staffCode=9622554");
        String sign = FormatUtil.packageSign( params,false );
        System.out.println(sign);
        sign= SHA1Util.encode(sign);
        return sign;
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 生成Authorize链接
     *
     * @param snsapiBase snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public String getAuthorizeURL(String callback_uri, boolean snsapiBase) {
        return getAuthorizeURL(appid, callback_uri, null, snsapiBase);
    }

    public String getAuthorizeURL(String appId, String redirectUri, String state, boolean snsapiBase) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirectUri);
        if (snsapiBase) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }
        if (state == null || state == "") {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        String para = FormatUtil.packageSign(params, false);
        return authorize_uri + "?" + para;
    }

    public String getTemplates() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", this.getAccessToken());
        String[] tmpStr = url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        return ret;
    }

    public String sendTemplate(String openId) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", this.getAccessToken());
        String body = "  {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"WTVww4pSu3lckyphCHhxvlOKWD7QS6iT3PEb8Lp-uGE\",    \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"直播主题：马上开始啦，9.9元抵500的海鲜大餐 ，一起吃吃看！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"黄岩农商行dou yin直播\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"2020-07-23 19:00:00\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\"douyin搜索黄岩农商银行\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }" +
                "           }\n" +
                "}";
        String ret = HttpUtils.sendPost(url, body);
        System.out.println(ret);
        return ret;
    }

    public String getUsers() {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", this.getAccessToken());
        String[] tmpStr = url.split("\\?");
        String ret = HttpUtils.sendGet(tmpStr[0], tmpStr[1]);
        return ret;
    }
}