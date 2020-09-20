package com.hyrcb.hydp.modules.crm.api;

import com.ruoyi.framework.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket/crm/lobby")
public class LobbyWebsocketController extends BaseController {
    private String prefix = "websocket/crm/lobby";
    @GetMapping()
    public String socket()
    {
        return prefix + "/websocket";
    }
}
