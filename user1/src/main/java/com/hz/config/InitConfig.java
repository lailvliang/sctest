package com.hz.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    static {
        //init GatewayBlockHandler
        GatewayCallbackManager.setBlockHandler(new GatewayBlockHandler());

        WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    }
}
