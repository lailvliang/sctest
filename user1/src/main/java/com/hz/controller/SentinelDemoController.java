package com.hz.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/test")
public class SentinelDemoController {

    /*
    限流
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource(value = "listBrand",blockHandler = "deaultBlockHandle")
    public String content() {
        return "hi!";
    }


    /*
    *异常数降级
    *异常数 (DEGRADE_GRADE_EXCEPTION_COUNT)：当资源近 1 分钟的异常数目超过阈值之后会进行熔断
     */
    public static int count = 0;
    @RequestMapping(value = "/exceptionCount", method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource(value = "exceptionCountBrand",fallback = "exceptionCountFallBlockHandle")
    public String downLevel() {
        if((++count)%2==0){
            throw new RuntimeException("异常降级");
        }
        return "success";
    }

    /*
    * 超时降级
    * 平均响应时间 (DEGRADE_GRADE_RT)：当资源的平均响应时间超过阈值（DegradeRule 中的 count，以 ms 为单位）之后，资源进入准降级状态。
    * 接下来如果持续进入 5 个请求，它们的 RT 都持续超过这个阈值，那么在接下的时间窗口（DegradeRule 中的 timeWindow，以 s 为单位）之内，
    * 对这个方法的调用都会自动地返回（抛出 DegradeException）。
    * */
    @RequestMapping(value = "/timeOut", method = RequestMethod.GET)
    @ResponseBody
    @SentinelResource(value = "timeOutBrand",fallback = "timeOutFallBlockHandle")
    public String timeOut() {
        try {
            Thread.sleep(3000); //控制台设置阀值200 连续5个请求响应时间超过200的话 就会熔断降级 此处模拟请求响应时间3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static String  timeOutFallBlockHandle(){
        System.out.println("超时降级");
        return "timeOutError";
    }


    public static String  exceptionCountFallBlockHandle(){
        System.out.println("异常数降级");
        return "exceptionCountError";
    }

    public static String  deaultBlockHandle(BlockException be){
        System.out.println("限流");
        return "error";
    }

}
