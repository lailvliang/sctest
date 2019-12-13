package com.hz.fallback;

import com.hz.service.UserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public String selectByUsername(String username) {
                System.out.println(throwable.getMessage()+"通过用户名查询用户异常:{}"+username);
                return "服务器出小差啦！";
            }

        };
    }
}
