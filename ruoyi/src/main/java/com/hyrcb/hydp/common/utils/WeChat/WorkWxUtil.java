package com.hyrcb.hydp.common.utils.WeChat;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jfinal.plugin.ehcache.CacheKit;
import com.ruoyi.common.utils.http.HttpUtils;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业微信工具类
 */
public class WorkWxUtil {
    //企业的id
    private String corpid = "ww8c091d6cde002d23";
    //每个应用一个secret
    private String corpsecret = "r_Qmyz1rdKElGXU59N7tEoSYQS9hB-5hUncxni8k-kE";
    //企业微信应用id
    private String agentId = "1000002";

    //腾讯企业微信服务器
    String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/";

    /**
     * 获取企业微信token
     *
     * @return
     */
    public String getAccessToken() {
//        WorkWxBen workWxBen = CacheKit.get(corpid, corpsecret);
//        if (workWxBen == null) {
        System.out.println("开始缓存123");
        WorkWxBen workWxBen = new WorkWxBen();
        String accessToken = this.doGetAccessToken();
        workWxBen.setCorpid(corpid);
        workWxBen.setCorpsecret(corpsecret);
        workWxBen.setAgentId(agentId);
        workWxBen.setAccessToken(accessToken);
        //CacheKit.put(corpid, corpsecret, workWxBen);
        //}
        return workWxBen.getAccessToken();
    }

    /**
     * 访问企业微信服务器获取AccessToken
     *
     * @return
     */
    private String doGetAccessToken() {
        String url = baseUrl + "gettoken";
        String param = "corpid=@ID&corpsecret=@SECRET";
        param = param.replace("@ID", corpid);
        param = param.replace("@SECRET", corpsecret);
        String ret = HttpUtils.sendGet(url, param);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        String accessToken = jsonObject.getStr("access_token");
        return accessToken;
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public String getUserInfo(String userId) {
        String url = baseUrl + "user/get";
        String param = "access_token=@ACCESS_TOKEN&userid=@USERID";
        param = param.replace("@ACCESS_TOKEN", this.getAccessToken());
        param = param.replace("@USERID", userId);
        String ret = HttpUtils.sendGet(url, param);
        return ret;
    }

    /**
     * 获取外部客户列表
     *
     * @param userId
     * @return
     */
    public List<String> getCustList(String userId) {
        String url = baseUrl + "externalcontact/list";
        String param = "access_token=@ACCESS_TOKEN&userid=@USERID";
        param = param.replace("@ACCESS_TOKEN", this.getAccessToken());
        param = param.replace("@USERID", userId);
        String ret = HttpUtils.sendGet(url, param);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        String listStr = jsonObject.getStr("external_userid");
        JSONArray jsonArray = JSONUtil.parseArray(listStr);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            list.add(jsonArray.get(i).toString());
        }
        return list;
    }

    /*
     * 获取客户信息
     * @param externalUserid
     * @return
     */
    public String getCustInfo(String externalUserid) {
        String url = baseUrl + "externalcontact/get";
        String param = "access_token=@ACCESS_TOKEN&external_userid=@EXTERNAL_USERID";
        param = param.replace("@ACCESS_TOKEN", this.getAccessToken());
        param = param.replace("@EXTERNAL_USERID", externalUserid);
        String ret = HttpUtils.sendGet(url, param);
        return ret;
    }

    public String sendMsg(String touser, String name,String id) {
        String url = baseUrl + "message/send?access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", this.getAccessToken());
        //格式化日期时间
        DateFormat df3 = DateFormat.getDateTimeInstance();
        //要格式化的Date对象
        Date date = new Date();
        String param = "{\n" +
                "   \"touser\" : \"" + touser + "\",\n" +
                "   \"toparty\" : \"PartyID1|PartyID2\",\n" +
                "   \"totag\" : \"TagID1 | TagID2\",\n" +
                "   \"msgtype\" : \"text\",\n" +
                "   \"agentid\" : 1000002,\n" +
                "   \"text\" : {\n" +
                "       \"content\" : \"客户:" + name + "</br>提交了一笔贷款申请，<a href=\\\"http://cc1212.natappvip.cc/crm/shop/ApplyInfo?staffCode=9622554&id="+id+"\\\">点击查看</a>！</br>" + df3.format(date) + "\"\n" +
                "   },\n" +
                "   \"safe\":0,\n" +
                "   \"enable_id_trans\": 0,\n" +
                "   \"enable_duplicate_check\": 0,\n" +
                "   \"duplicate_check_interval\": 1800\n" +
                "}";

        String ret = HttpUtils.sendPost(url, param);
        return ret;
    }
}
