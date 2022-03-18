package com.shine.controller;

import com.shine.service.StrategyMain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * 测试
 * @author wb-cb236432
 * @create 2018-06-22 10:09
 **/
@RestController
@Slf4j
public class DemoController {
    @Resource
    private List<StrategyMain> strategys;

    /**
     * 有赞小程序后台填写的token
     */
    private static String TOKEN = "youniyouzan";


    @RequestMapping("/official/account/callback/{appid}")
    public String officialAccountCallback(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid){
        if (StringUtils.isEmpty(appid)) {
            return "ok";
        }
        String method = request.getMethod().toLowerCase();
        if ("get".equals(method)) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            log.info("公众号-微信开发者验证参数，signature:{}, timestamp:{}, nonce:{}, echostr:{}, method:{}", signature, timestamp, nonce, echostr, method);
            if (checkSignature(signature, timestamp, nonce)) {
                log.info("公众号-微信开发者验证成功");
                return echostr;
            }
        }
        if ("post".equals(method)) {
            System.out.println(request);
            System.out.println(response);
            System.out.println(appid);
            return "";
        }
        log.warn("公众号-微信开发者-验证，非法的请求方法：method:{}", method);
        return "success";
    }

    public boolean checkSignature(String signature, String timestamp, String nonce) {
        //TOKEN
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;

        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    private String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }







}
