package com.hz.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hz.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-producer", fallbackFactory = UserServiceFallbackFactory.class, decode404 = true)
public interface UserService {
    /**
     * feign rpc访问远程/users/{username}接口
     * @param name
     * @return
     */
    @GetMapping(value = "/users/{name}")
    String selectByUsername(@PathVariable("name") String name);

}
