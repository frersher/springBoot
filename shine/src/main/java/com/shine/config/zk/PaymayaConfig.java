package com.shine.config.zk;
 
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
 
/**
 * Pgp 基础信息配置
 * User: jiangXueZhi
 * Date: 2022/02/17
 */
@Data
@Configuration
public class PaymayaConfig {
 
    /**
     * GPG加解密——公钥
     */
    public static String GPG_PUBLIC_KEY = "xxx";
 
    /**
     * GPG加解密——公钥的文件名
     */
    public static String GPG_PUBLIC_KEY_FILENAME = "public-key.gpg";
 
    /**
     * GPG加解密——私钥
     */
    public static String GPG_PRIVATE_KEY = "xxx";
 
    /**
     * GPG加解密——私钥的key
     */
    public static String GPG_PRIVATE_KEY_PWD = "xxx";
 
    /**
     * GPG加解密——私钥的文件名
     */
    public static String GPG_PRIVATE_KEY_FILENAME = "private-key.gpg";
}