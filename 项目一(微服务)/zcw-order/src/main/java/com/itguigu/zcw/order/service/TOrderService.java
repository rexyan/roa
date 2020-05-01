package com.itguigu.zcw.order.service;

import com.itguigu.zcw.order.bean.TOrder;
import com.itguigu.zcw.order.service.vo.req.AddOrderReqVo;

public interface TOrderService {
	
	/**
	 * 保存订单信息
	 * @param addOrderReqVo
	 * @return
	 */
	TOrder saveOrder(AddOrderReqVo addOrderReqVo);

}
