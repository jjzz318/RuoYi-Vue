package com.hyrcb.hydp.common.utils.WeChat;

import com.ruoyi.common.utils.http.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class WxMenu {
    public String accessToken = "";
    private String baseUrl = "https://api.weixin.qq.com/cgi-bin/menu/";

    public WxMenu(String _accessToken) {
        this.accessToken = _accessToken;
        System.out.println(this.accessToken);
    }

    private List<WxMenuBen> menuList = new ArrayList<WxMenuBen>();
    private List<WxMenuBen> menuSubList = new ArrayList<WxMenuBen>();

    /**
     * 添加主菜单
     *
     * @param id
     * @param name     菜单名称
     * @param key      菜单KEY值，用于消息接口推送，不超过128字节
     * @param url
     * @param existSub
     */
    public void addMenu(String id, String name, String key, String url, boolean existSub) {
        WxMenuBen wxMenuBen = new WxMenuBen();
        wxMenuBen.setName(name);
        wxMenuBen.setId(id);
        wxMenuBen.setParentId("0");

        wxMenuBen.setUrl(url);
        if (url.equals("")) {
            wxMenuBen.setKey(key);
            wxMenuBen.setType("click");
        } else {
            wxMenuBen.setType("view");
        }
        wxMenuBen.setExistSub(existSub);
        menuList.add(wxMenuBen);
    }

    /**
     * 添加子菜单
     *
     * @param parentId 主菜单的ID
     * @param name     名称
     * @param key      菜单KEY值，用于消息接口推送，不超过128字节
     * @param url
     */
    public void addSubMenu(String parentId, String name, String key, String url) {
        WxMenuBen wxMenuBen = new WxMenuBen();
        wxMenuBen.setName(name);
        wxMenuBen.setParentId(parentId);

        wxMenuBen.setUrl(url);
        if (url.equals("")) {
            wxMenuBen.setKey(key);
            wxMenuBen.setType("click");
        } else {
            wxMenuBen.setType("view");
        }

        menuSubList.add(wxMenuBen);
    }

    private String getCreateMenuJson() {
        String menuStr = "{'button':[";
        for (WxMenuBen menu : menuList) {
            menuStr += "{'name':'" + menu.getName() + "'";
            if (!menu.getKey().equals("")) {
                menuStr += ",'key':'" + menu.getKey() + "'";
            }
            if (!menu.getUrl().equals("")) {
                menuStr += ",'url':'" + menu.getUrl() + "'";
            }
            if (!menu.getType().equals("")) {
                menuStr += ",'type':'" + menu.getType() + "'";
            }
            if (menu.isExistSub()) {
                String menuSubStr = ",'sub_button':[";
                for (WxMenuBen menuSub : menuSubList) {
                    if (menu.getId().equals(menuSub.getParentId())) {
                        menuSubStr += "{'name':'" + menuSub.getName() + "'";
                        if (!menuSub.getKey().equals("")) {
                            menuSubStr += ",'key':'" + menuSub.getKey() + "'";
                        }
                        if (!menuSub.getType().equals("")) {
                            menuSubStr += ",'type':'" + menuSub.getType() + "'";
                        }
                        if (!menuSub.getUrl().equals("")) {
                            menuSubStr += ",'url':'" + menuSub.getUrl() + "'";
                        }
                        menuSubStr += "},";
                    }
                }
                menuSubStr = menuSubStr.substring(0, menuSubStr.length() - 1) + "]";
                menuStr += menuSubStr;
            }
            menuStr += "},";
        }
        menuStr = menuStr.substring(0, menuStr.length() - 1) + "]}";
        menuStr = menuStr.replace("'", "\"");
        System.out.println(menuStr);
        return menuStr;
    }

    public String getMenu() {
        String url = baseUrl + "get";
        String param="access_token=@ACCESS_TOKEN";
        param = param.replace("@ACCESS_TOKEN", accessToken);
        String ret = HttpUtils.sendGet(url, param);
        System.out.println("菜单:" + ret);
        return ret;
    }

    public String createMenu() {
        String url = baseUrl + "create?access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", accessToken);
        String ret = HttpUtils.sendPost(url, this.getCreateMenuJson());
        System.out.println(ret);
        return ret;
    }

    public String deleteMenu() {
        String url = baseUrl + "delete";
        String param="access_token=@ACCESS_TOKEN";
        url = url.replace("@ACCESS_TOKEN", accessToken);
        String ret = HttpUtils.sendGet(url, param);
        return ret;
    }
}
