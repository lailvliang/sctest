package com.hz.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    static {
        //设置统一限流降级扩展实现
        WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    }
}
