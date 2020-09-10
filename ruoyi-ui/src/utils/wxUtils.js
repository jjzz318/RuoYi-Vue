import request from '@/utils/request'
import wx from "weixin-js-sdk";
export function wxShare(title,desc,link,imgUrl) {
    getJsApiSignature(link).then((response) => {
        let data = response.data;
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: data.appId, // 必填，公众号的唯一标识
            timestamp: data.timestamp, // 必填，生成签名的时间戳
            nonceStr: data.nonceStr, // 必填，生成签名的随机串
            signature: data.signature, // 必填，签名，见附录1
            jsApiList: ["updateAppMessageShareData", "updateTimelineShareData", "onMenuShareTimeline"], // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            wx.updateAppMessageShareData({
                title: title, // 分享标题
                desc: desc, // 分享描述
                link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl:imgUrl, // 分享图标
                success: function () {
                   
                },
            });
            wx.updateTimelineShareData({
                title: title, // 分享标题
                link: link, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl:imgUrl, // 分享图标
                success: function () {
                
                },
            });
        })
    });

}

export function getJsApiSignature(url) {
    return request({
        url: '/wx/getJsApiSignature',
        method: 'post',
        data: url
    })
}
export function getAuthorizeUrl(redirectUri,currentUrl) {
    const data = {
        redirectUri,
        currentUrl
      }
    return request({
        url: '/wx/getAuthorizeUrl',
        method: 'post',
        data: data
    })
}

export function getWxUserInfo(openid,staffCode) {
    return request({
        url: '/wx/getWxUserInfo?openid='+openid+'&staffCode='+staffCode,
        method: 'get'
    })
}

export function recordClick(openId,staffCode,productId) {
    const data = {
        openId,
        staffCode,
        productId
      }
    return request({
        url: '/wx/recordClick',
        method: 'post',
        data: data
    })
}