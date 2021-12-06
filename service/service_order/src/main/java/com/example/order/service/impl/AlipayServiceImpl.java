package com.example.order.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.order.config.AlipayConfig;
import com.example.order.entity.TOrder;
import com.example.order.service.AlipayService;
import com.example.order.service.TOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private TOrderService orderService;

    /** 调取支付宝接口 web端支付 */
    DefaultAlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.ALIPAY_GATEWAY_URL, AlipayConfig.ALIPAY_APP_ID,
            AlipayConfig.ALIPAY_RSA_PRIVATE_KEY, "json", AlipayConfig.ALIPAY_CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
            AlipayConfig.ALIPAY_SIGN_TYPE);

    @Override
    public String alipay(String orderNo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /** 同步通知，支付完成后，支付成功页面 */
        alipayRequest.setReturnUrl(AlipayConfig.ALIPAY_RETURN_URL);
        /** 异步通知，支付完成后，需要进行的异步处理 */
        alipayRequest.setNotifyUrl(AlipayConfig.ALIPAY_NOTIFY_URL);

        //通过 orderId 查询订单信息
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder order = orderService.getOne(wrapper);
        BigDecimal total_amount = order.getTotalFee();
        String subject = order.getCoachNo();
        String timeout_express = "30m";     //30分钟后将关闭交易

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNo + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String refund(String outTradeNo, String refundReason, String refundAmount, String outRequestNo) {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        /** 调取接口 */
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"refund_amount\":\"" + Integer.valueOf(refundAmount)
                + "\"," + "\"refund_reason\":\"" + refundReason + "\"," + "\"out_request_no\":\"" + outRequestNo
                + "\"}");
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }
}
