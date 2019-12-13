package com.hz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class SentinelTestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource(value = "listBrand",blockHandler = "deaultBlockHandle")
    public String content() {
        return "Hello!";
    }

    public static int count = 0;
    @RequestMapping(value = "/downLevel", method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource(value = "downLevelBrand",fallback = "fallBlockHandle")
    public String downLevel() {
        if((++count)%2==0){
            throw new RuntimeException("异常降级");
        }
        return "downLevel!";
    }

    public static String  fallBlockHandle(){
        System.out.println(11111);
        return "error";
    }

    public static String  deaultBlockHandle(BlockException be){
        return "error";
    }

}
