package com.dbshop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeliveryCancelProcHandler implements DbshopHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�̰� �� �ȵǴ��� ������ �𸣰ڴ�
		
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		
		Dao dao = new Dao();
		dao.deliveryCancel(orderNum); //"UPDATE orders SET result=1 WHERE orderNum=?";

		return "Delivery.do";
	}

}
