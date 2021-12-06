package com.example.order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AlipayService {
    /**
     * 支付宝支付调用接口
     * @param response
     * @param request
     * @throws IOException
     */
    String alipay(String orderId, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     * 退款
     *
     * @param outTradeNo   订单编号
     * @param refundReason 退款原因
     * @param refundAmount 退款金额
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    String refund(String outTradeNo, String refundReason, String refundAmount, String outRequestNo);
}
