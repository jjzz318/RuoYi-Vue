/**
 * File Name: BaseTest.java
 * Date: 2020-05-28 17:23:35
 */
package com.hyrcb.hydp;

import com.hyrcb.hydp.common.utils.WeChat.WorkWxUtil;
import com.hyrcb.hydp.common.utils.WeChat.WxUtil;
import me.chanjar.weixin.common.service.WxService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.LaunchEntry;

/**
 * Description: 
 * @author shenzulun
 * @date 2020-05-28
 * @version 1.0
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = LaunchEntry.class)
public class BaseTest {
    @Autowired
    private WxMpService wxMpService;
    @Test
	public void WorkWxTest(){
        WorkWxUtil workWxUtil=new WorkWxUtil();
        String accessToken=workWxUtil.getAccessToken();
        System.out.println(accessToken);
        //String ret=workWxUtil.getUserInfo("ChenChong");
        String ret=workWxUtil.sendMsg("ChenChong|JinLin","陈翀","29");
        System.out.println(ret);
    }
    @Test
    public void WxTest(){

        WxUtil wxUtil=new WxUtil();
        String wx=wxUtil.getJsApiSignature("");
        System.out.println(wx);
    }
}
