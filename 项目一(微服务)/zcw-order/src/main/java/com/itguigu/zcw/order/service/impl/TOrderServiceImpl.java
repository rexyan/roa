package com.itguigu.zcw.order.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.itguigu.zcw.commons.bean.TReturn;
import com.itguigu.zcw.commons.enums.OrderStatusEnumes;
import com.itguigu.zcw.commons.utils.AppDateUtils;
import com.itguigu.zcw.commons.vo.resp.AppResponse;
import com.itguigu.zcw.order.bean.TOrder;
import com.itguigu.zcw.order.mapper.TOrderMapper;
import com.itguigu.zcw.order.service.TOrderService;
import com.itguigu.zcw.order.service.TProjectServiceFeign;
import com.itguigu.zcw.order.service.vo.req.AddOrderReqVo;

@Service
public class TOrderServiceImpl implements TOrderService {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TOrderMapper orderMapper;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;
	
	@Override
	public TOrder saveOrder(AddOrderReqVo addOrderReqVo) {
		TOrder tOrder = new TOrder();
		
		// 根据 accessToken 获取 member id
		String accessToken = addOrderReqVo.getAccessToken();
		String memberid = stringRedisTemplate.opsForValue().get(accessToken);
		
		// 生成订单号
		String ordernum = UUID.randomUUID().toString().replace("-", "");
		
		// 根据回报 ID 查询回报信息
		String returnid = addOrderReqVo.getReturnid();
		
		// 根据众筹数量，价格，邮费，重新计算支付价格（防止这期间价格变动更新）
		AppResponse<TReturn> tReturnAppResponse = projectServiceFeign.getReturnDetailById(Integer.parseInt(returnid));
		TReturn tReturn = tReturnAppResponse.getData();
		Integer totalPrice = tReturn.getSupportmoney() * Integer.parseInt(addOrderReqVo.getRtncount()) + tReturn.getFreight();
		
		// 封装 TOrder 
		tOrder.setMemberid(Integer.parseInt(memberid));
		tOrder.setProjectid(Integer.parseInt(addOrderReqVo.getProjectid()));
		tOrder.setReturnid(Integer.parseInt(addOrderReqVo.getReturnid()));
		tOrder.setOrdernum(ordernum);
		tOrder.setCreatedate(AppDateUtils.getFormatTime());
		tOrder.setMoney(totalPrice);
		tOrder.setRtncount(Integer.parseInt(addOrderReqVo.getRtncount()));
		tOrder.setStatus(OrderStatusEnumes.UNPAY.getCode() + "");
		tOrder.setAddress(addOrderReqVo.getAddress());
		tOrder.setInvoice(addOrderReqVo.getInvoice() + "");
		tOrder.setInvoictitle(addOrderReqVo.getInvoictitle());
		tOrder.setRemark(addOrderReqVo.getRemark());
		
		orderMapper.insertSelective(tOrder);
		return tOrder;
	}

}
