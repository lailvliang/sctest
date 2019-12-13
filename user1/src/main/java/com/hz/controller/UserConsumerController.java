package com.hz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserConsumerController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getUser/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable String name) {

        return userService.selectByUsername(name);
    }

}
