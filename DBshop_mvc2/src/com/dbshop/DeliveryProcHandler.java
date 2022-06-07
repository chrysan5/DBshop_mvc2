package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliveryProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		
		Dao dao = new Dao();
		dao.delivery(orderNum); //"UPDATE orders SET result=2 WHERE orderNum=?";
		
		return "Delivery.do";
	}

}
