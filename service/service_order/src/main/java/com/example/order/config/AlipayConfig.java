package com.example.order.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class AlipayConfig implements InitializingBean {
    @Value("${alipay.app_id}")
    private String appId;               //对应支付宝账号的APPID
    @Value("${alipay.rsa_private_key}")
    private String rsaPrivateKey;       //RSA私钥
    @Value("${alipay.alipay_public_key}")
    private String alipayPublicKey;        //支付宝公钥
    @Value("${alipay.notify_url}")
    private String notifyUrl;           //异步通知，设计自己的后台
    @Value("${alipay.return_url}")      //付款成功后跳转的页面，只跳转一次
    private String returnUrl;           //页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.sign_type}")
    private String signType;            //签名方式：RSA2
    @Value("${alipay.charset}")
    private String charset;             //字符编码格式：utf-8
    @Value("${alipay.gateway_url}")
    private String gatewayUrl;          //支付宝网关
    @Value("${alipay.log_path}")
    private String logPath;             //日志文件路径
    @Value("${alipay.format}")
    private String format;

    public static String ALIPAY_APP_ID;
    public static String ALIPAY_RSA_PRIVATE_KEY;
    public static String ALIPAY_PUBLIC_KEY;
    public static String ALIPAY_NOTIFY_URL;
    public static String ALIPAY_RETURN_URL;
    public static String ALIPAY_SIGN_TYPE;
    public static String ALIPAY_CHARSET;
    public static String ALIPAY_GATEWAY_URL;
    public static String ALIPAY_LOG_PATH;
    public static String ALIPAY_FORMAT;

    @Override
    public void afterPropertiesSet() throws Exception {
        ALIPAY_APP_ID = this.appId;
        ALIPAY_RSA_PRIVATE_KEY = this.rsaPrivateKey;
        ALIPAY_PUBLIC_KEY = this.alipayPublicKey;
        ALIPAY_NOTIFY_URL = this.notifyUrl;
        ALIPAY_RETURN_URL = this.returnUrl;
        ALIPAY_SIGN_TYPE = this.signType;
        ALIPAY_CHARSET = this.charset;
        ALIPAY_GATEWAY_URL = this.gatewayUrl;
        ALIPAY_LOG_PATH = this.logPath;
        ALIPAY_FORMAT = this.format;
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(ALIPAY_LOG_PATH + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
