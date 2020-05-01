package com.itguigu.zcw.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.commons.vo.resp.ReturnPayConfirmRespVo;
import com.itguigu.zcw.commons.vo.resp.UserLoginRespVo;
import com.itguigu.zcw.web.bean.TOrder;
import com.itguigu.zcw.web.config.AlipayConfig;
import com.itguigu.zcw.web.service.TOrderServiceFeign;
import com.itguigu.zcw.web.vo.req.AddOrderReqVo;
import com.itguigu.zcw.web.vo.req.SubmitOrderInfoReqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TOrderController {
	@Autowired
	TOrderServiceFeign orderServiceFeign;
	
	@ResponseBody
	@PostMapping("/order/pay")
	public String orderPay(SubmitOrderInfoReqVo submitOrderInfoReqVo, HttpSession session) {
		AddOrderReqVo addOrderReqVo = new AddOrderReqVo();
		
		// 1. 下单
		BeanUtils.copyProperties(submitOrderInfoReqVo, addOrderReqVo);
		// 从用户登录信息中获取 accessToken 信息
		UserLoginRespVo userLoginRespVo = (UserLoginRespVo) session.getAttribute("memeber");
		addOrderReqVo.setAccessToken(userLoginRespVo.getAccessToken());
		// 从 session 中获取之前保存的 确认回报内容
		ReturnPayConfirmRespVo returnPayConfirmRespVo = (ReturnPayConfirmRespVo) session.getAttribute("projectConfirmInfo");
		if(userLoginRespVo == null || returnPayConfirmRespVo==null) {
			session.setAttribute("redirectUrl", "/order/pay");
			return "redirect:/login";
		}
		
		addOrderReqVo.setProjectid(returnPayConfirmRespVo.getProjectId().toString());
		addOrderReqVo.setReturnid(returnPayConfirmRespVo.getReturnId().toString());
		addOrderReqVo.setRtncount(returnPayConfirmRespVo.getNum().toString());
		AppResponse<TOrder> saveOrdeResponse = orderServiceFeign.saveOrder(addOrderReqVo);
		TOrder orderData = saveOrdeResponse.getData();
		
		// 2. 支付
		String prodName = "众筹网众筹商品" + addOrderReqVo.getProjectid();
		String payPage = PayOrder(orderData.getOrdernum(), orderData.getMoney(), prodName, addOrderReqVo.getRemark());
		return payPage;
	}
	
	
	/**
	 * 支付宝异步回调，逻辑处理完成后需要返回给支付宝 success
	 * @return
	 */
	@ResponseBody
	@PostMapping("/order/payAsync")
	public String payAsync() {
		// 更新订单等信息
		
		// 逻辑处理完成后需要返回给支付宝 success
		return "success";
	}
	
	/**
	 * 支付宝支付。此接口调用蚂蚁金服接口，返回一个表单，需要将表单返回给浏览器，表单会自动提交，提交成功后会返回扫码接口
	 * @param ordernum  订单编号
	 * @param money  订单金额
	 * @param prodName  订单名称
	 * @param remark  订单描述
	 * @return
	 */
	private String PayOrder(String ordernum, Integer money, String prodName, String remark) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ ordernum +"\"," 
				+ "\"total_amount\":\""+ money +"\"," 
				+ "\"subject\":\""+ prodName +"\"," 
				+ "\"body\":\""+ remark +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			return result;
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
