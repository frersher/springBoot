package com.shine.annotation;

import com.alibaba.fastjson.JSON;
import com.shine.basic.SysLog;
import com.shine.service.SysLogService;
import com.shine.util.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Resource
    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.shine.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            //保存获取的操作
            sysLog.setOperation(value);
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setCreateDate(new Date());
        //获取用户名
        sysLog.setUsername(ShiroUtils.getSysUser().getUserNick());

        //调用service保存SysLog实体类到数据库
        sysLogService.saveSysLog(sysLog);
    }


    public static void main(String[] args)
    {
        try
        {
            BufferedImage appletImg = ImageIO.read(new FileInputStream(new File("/Users/chenbang/Desktop/111.png")));
            Graphics2D g2d = appletImg.createGraphics();

            // 设置抗锯齿的属性
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            BufferedImage centerImg = ImageIO.read(new File("/Users/chenbang/Desktop/222.png"));
            g2d.drawImage(centerImg.getScaledInstance(centerImg.getWidth(), centerImg.getHeight(), Image.SCALE_SMOOTH), (appletImg.getWidth() - centerImg.getWidth()) / 2, (appletImg.getHeight() - centerImg.getHeight()) / 2, null);

            // 关闭资源
            g2d.dispose();
            ImageIO.write(appletImg, "png", new File("/Users/chenbang/Desktop/222.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}