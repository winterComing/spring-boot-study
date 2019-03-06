package com.unicom.boot.baseweb.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *  http服务端：通过spring框架，不同方法获取请求参数
 *  get请求参数：httpServletRequest.getParameter("name")；@RequestParam("name") String param；
 *              @RequestParam String name；String name 均可以收到 ?/name=dengh的请求参数
 *              http客户端需要URLEncoder.encode(string,charset);
 *  post请求参数：
 *          content-type为application/x-www-form-urlencoded，（age=20&sex="男"）可以使用：
 *              request.getParameter("sex")；@RequestParam String sex；@RequestParam("age") String age；
 *              String sex 来接收
 *          content-type为application/json，可以使用：
 *
 *
 *
 * @author dengh
 */
@Controller
public class HttpServerDemo {

    public static final Logger logger = LoggerFactory.getLogger(HttpUrlConnectionUtils.class);

    @GetMapping("/testParam1")
    @ResponseBody
    public void testParam(HttpServletRequest httpServletRequest){
        logger.info("获取到get请求参数：{}", httpServletRequest.getParameter("name"));
    }

    @GetMapping("/testParam2")
    @ResponseBody
    public void testParam2(@RequestParam("name") String param){
        logger.info("获取到get请求参数：{}", param);
    }

    @GetMapping("/testParam3")
    @ResponseBody
    public void testParam3(@RequestParam String name){
        logger.info("获取到get请求参数：{}", name);
    }

    @GetMapping("/testParam4")
    @ResponseBody
    public void testParam4(String name){
        logger.info("获取到get请求参数：{}", name);
    }

    @PostMapping("/testPost1")
    @ResponseBody
    public void testPost1(HttpServletRequest request){
        logger.info("收到testPost1参数：{}，{}", request.getParameter("sex"), request.getParameter("age"));
    }

    @PostMapping("testPost2")
    @ResponseBody
    public void testPost2(@RequestParam String sex, @RequestParam("age") String age  ){
        logger.info("收到testPost2参数：{}，{}", sex, age);
    }

    @PostMapping("/testPost3")
    @ResponseBody
    public void testPost3(String sex, String age){
        logger.info("收到testPost3参数：{}，{}", sex, age);
    }

    @PostMapping("/testPost4")
    @ResponseBody
    public void testPost4(@RequestBody String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        logger.info("收到testPost4参数：{}，{}", jsonStr, jsonObject.getString("sex"), jsonObject.getString("age"));
    }



}
