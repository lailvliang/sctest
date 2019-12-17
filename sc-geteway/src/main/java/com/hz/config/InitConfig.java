package com.hz.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    static {
        //设置网关限流扩展实现
        GatewayCallbackManager.setBlockHandler(new GatewayBlockHandler());
    }
}
