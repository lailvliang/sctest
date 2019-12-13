package com.hzed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserProductionController {
    public static int count = 0;
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable String name) {
        if((++count)%2==0){
            throw new RuntimeException("服务异常");
        }
        System.out.println("Hello "+name);
        return name;
    }
}
